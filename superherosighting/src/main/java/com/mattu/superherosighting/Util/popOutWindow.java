package com.mattu.superherosighting.Util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class popOutWindow {
    public void popOut(String msg, HttpServletResponse rep) throws IOException {
        PrintWriter out = rep.getWriter();
        rep.setContentType("text/html;charset=utf-8");
        out.print("<script>alert('"+msg+"')</script>");
        out.flush();
    }
}
