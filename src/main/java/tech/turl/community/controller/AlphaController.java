package tech.turl.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.turl.community.service.AlphaService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot.";
    }
    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }
//    @RequestMapping("/http")
//    public void http(HttpServletRequest request, HttpServletResponse response) {
//        System.out.println(request.getMethod());// 请求方式
//        System.out.println(request.getServletPath());// 请求路径
//        Enumeration<String> enumeration = request.getHeaderNames();
//        while (enumeration.hasMoreElements()){
//            String name = enumeration.nextElement();
//            String value = request.getHeader(name);
//            System.out.println(name + " : " +  value);
//        }
//        System.out.println(request.getParameter("code"));
//        // 返回响应数据
//        response.setContentType("text/html;charset=utf-8");// 响应类型
//
//        try (PrintWriter writer= response.getWriter()){// 放在小括号里面流会自动关闭
//            writer.write("<h1>论坛<h1>");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    // 带参数GET请求
    //  /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)// 只能处理GET请求
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "20") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // 路径中带参数
    // /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "success";
    }

    // 响应表单请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 响应HTML数据
    @RequestMapping(path="/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",30);
        mav.setViewName("demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name", "Beijing");
        model.addAttribute("age", 23);
        return "/demo/view";
    }

    // 响应JSON数据(异步请求)
    // Java对象 -> JSON字符串 -> JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 3000);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 3000);
        list.add(emp);
        emp = new HashMap<>();
        emp.put("name", "李四");
        emp.put("age", 24);
        emp.put("salary", 4000);
        list.add(emp);
        emp = new HashMap<>();
        emp.put("name", "John");
        emp.put("age", 23);
        emp.put("salary", 3000);
        list.add(emp);
        return list;
    }
}
