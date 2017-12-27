package chapter42; 

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import Acme.JPM.Encoders.GifEncoder;

public class ImageContentWithDrawing extends HttpServlet {
  /** Initialize variables */
  private final static int width = 300;
  private final static int height = 300;

  /** Process the HTTP Get request */
  public void doGet(HttpServletRequest request, HttpServletResponse
      response) throws ServletException, IOException {
    response.setContentType("image/gif");
    OutputStream out = response.getOutputStream();

    // Create image
    Image image = new BufferedImage(width, height,
      BufferedImage.TYPE_INT_ARGB);

    // Get Graphics context of the image
    Graphics g = image.getGraphics();

    drawClock(g); // Draw a clock on graphics

    // Encode the image and send to the output stream
    new GifEncoder(image, out, true).encode();

    out.close(); // Close stream
  }

  private void drawClock(Graphics g) {
    // Initialize clock parameters
    int clockRadius =
      (int)(Math.min(width, height) * 0.7 * 0.5);
    int xCenter = (width) / 2;
    int yCenter = (height) / 2;

    // Draw circle
    g.setColor(Color.black);
    g.drawOval(xCenter - clockRadius,yCenter - clockRadius,
      2 * clockRadius, 2 * clockRadius);
    g.drawString("12", xCenter - 5, yCenter - clockRadius + 12);
    g.drawString("9", xCenter - clockRadius + 3, yCenter + 5);
    g.drawString("3", xCenter + clockRadius - 10, yCenter + 3);
    g.drawString("6", xCenter - 3, yCenter + clockRadius - 3);

    // Get current time using GregorianCalendar
    TimeZone timeZone = TimeZone.getDefault();
    GregorianCalendar cal = new GregorianCalendar(timeZone);

    // Draw second hand
    int second = (int)cal.get(GregorianCalendar.SECOND);
    int sLength = (int)(clockRadius * 0.9);
    int xSecond = (int)(xCenter + sLength * Math.sin(second * 
      (2 * Math.PI / 60)));
    int ySecond = (int)(yCenter - sLength * Math.cos(second *
      (2 * Math.PI / 60)));
    g.setColor(Color.red);
    g.drawLine(xCenter, yCenter, xSecond, ySecond);

    // Draw minute hand
    int minute = (int)cal.get(GregorianCalendar.MINUTE);
    int mLength = (int)(clockRadius * 0.75);
    int xMinute = (int)(xCenter + mLength * Math.sin(minute *
      (2 * Math.PI / 60)));
    int yMinute = (int)(yCenter - mLength * Math.cos(minute *
      (2 * Math.PI / 60)));
    g.setColor(Color.blue);
    g.drawLine(xCenter, yCenter, xMinute, yMinute);

    // Draw hour hand
    int hour = (int)cal.get(GregorianCalendar.HOUR_OF_DAY);
    int hLength = (int)(clockRadius * 0.6);
    int xHour = (int)(xCenter + hLength * Math.sin((hour + minute
      / 60.0) * (2 * Math.PI / 12)));
    int yHour = (int)(yCenter - hLength * Math.cos((hour + minute 
      / 60.0) * (2 * Math.PI / 12)));
    g.setColor(Color.green);
    g.drawLine(xCenter, yCenter, xHour, yHour);

    // Set display format in specified style, locale and time zone
    DateFormat formatter = DateFormat.getDateTimeInstance
      (DateFormat.MEDIUM, DateFormat.LONG);

    // Display current date
    g.setColor(Color.red);
    String today = formatter.format(cal.getTime());
    FontMetrics fm = g.getFontMetrics();
    g.drawString(today, (width -
      fm.stringWidth(today)) / 2, yCenter + clockRadius + 30);
  }
}
