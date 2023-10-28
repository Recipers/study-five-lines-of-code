package food869.was.lib.container;

import food869.was.bin.CommonClassLoader;
import food869.was.lib.request.Request;
import food869.was.lib.request.RequestMaker;
import food869.was.lib.response.Response;
import food869.was.lib.servlet.Servlet;
import food869.was.lib.session.Session;
import food869.was.lib.session.SessionManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.UUID;

public class WebServer {

  private final int PORT = 8088;

  private CommonClassLoader commonClassLoader;
  private HashMap<String, Object> servletMap;
  private SessionManager sessionManager;

  public WebServer(CommonClassLoader commonClassLoader) {
    this.commonClassLoader = commonClassLoader;
    servletMap = new HashMap<>();
    sessionManager = getSessionManager();
  }

  void runServer(HashMap<String, String> urlMap) {
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      System.out.println("Server Start in " + PORT + ".....");
      Socket socket;
      while ((socket = serverSocket.accept()) != null) {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Request request = new RequestMaker(in).makeRequest();
        Response response = new Response(out);
        String url = request.getURL();
        service(urlMap, request, response, url, out);
      }
    } catch (IOException e) {
      System.out.println("Port " + PORT + " already using...");
    }
  }

  private void service(HashMap<String, String> urlMap, Request request, Response response,
      String url, PrintWriter out) {
    if (urlMap.keySet().contains(url)) {
      setResponseOK(response);
      setCookie(request, response);
      serviceServlet(urlMap, request, response, url, out);
    } else {
      writeStaticFiles(response, url);
    }
  }

  private void serviceServlet(HashMap<String, String> urlMap, Request request, Response response,
      String url, PrintWriter out) {
    try {
      Class<?> appServletClasses = commonClassLoader.getChild(request.getPath())
          .loadClass(urlMap.get(url));
      if (servletMap.containsKey(url)) {
        serviceExistsServlet(request, response, url);
      } else {
        serviceNewServlet(appServletClasses, request, response, url);
      }
    } catch (Exception e) {
      writeError(out, Response.SERVER_ERR);
    }
  }

  private void serviceExistsServlet(Request request, Response response, String url) {

    Servlet servlet = (Servlet) servletMap.get(url);
    servlet.service(request, response);
  }

  private void serviceNewServlet(Class<?> appServletClasses, Request request, Response response,
      String url) throws InstantiationException, IllegalAccessException {

    Servlet servlet = (Servlet) appServletClasses.newInstance();
    servlet.init();
    servletMap.put(url, servlet);
    servlet.service(request, response);
  }

  private void writeStaticFiles(Response response, String url) {
    try {
      File file = new File("../webapps" + url);
      FileReader fileReader = new FileReader(file);
      writeStaticData(fileReader, response);
      fileReader.close();
      response.getWriter().close();
    } catch (IOException e) {
      writeError(response.getWriter(), Response.NOT_FOUND);
    }
  }

  private void writeStaticData(FileReader fileReader, Response response) throws IOException {
    response.getWriter().write("HTTP/1.1 ");
    response.setStatus(Response.RESPONSE_OK);
    int data;
    while ((data = fileReader.read()) > 0) {
      response.getWriter().write(data);
    }
  }

  private void setResponseOK(Response response) {
    response.getWriter().write("HTTP/1.1 ");
    response.setStatus(Response.RESPONSE_OK);
  }

  private void setCookie(Request request, Response response) {
    if (request.getSession() == null) {
      sessionManager.remove(request.getPath());
    }
    if (!sessionManager.contains(request.getPath())) {
      String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().substring(0, 30);
      sessionManager.add(request.getPath(), new Session(id));
      saveSessionManager();
      response.getWriter().write("Set-Cookie: RSESSIONID=" + id + "\r\n\r\n");
    } else {
      response.getWriter().write("\r\n\r\n");
    }
  }

  private SessionManager getSessionManager() {
    try {
      FileInputStream fs = new FileInputStream("Session.ser");
      ObjectInputStream os = new ObjectInputStream(fs);
      SessionManager sessionManager = (SessionManager) os.readObject();
      os.close();
      return sessionManager;
    } catch (Exception e) {
      return new SessionManager();
    }
  }

  private void saveSessionManager() {
    try {
      FileOutputStream fs = new FileOutputStream("Session.ser");
      ObjectOutputStream os = new ObjectOutputStream(fs);
      os.writeObject(sessionManager);
      os.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void writeError(PrintWriter out, int code) {
    out.write("HTTP/1.1 " + code + "\r\n\r\n");
    if (code == Response.NOT_FOUND) {
      out.write("<html><body><h1>Not Found</h1></body></html>");
    } else if (code == Response.SERVER_ERR) {
      out.write("<html><body><h1>Server Error</h1></body></html>");

    }
    out.close();
  }

}
