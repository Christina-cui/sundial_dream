package com.cuijing.sundial_dream.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cuijing.sundial_dream.entity.ActivityInfo;
import com.cuijing.sundial_dream.entity.SignUp;
import com.cuijing.sundial_dream.service.ActivityInfoService;
import com.cuijing.sundial_dream.service.SignUpService;
import com.cuijing.sundial_dream.service.UserService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.chengxusheji.utils.ExportExcelUtil;
import com.chengxusheji.utils.UserException;
import com.chengxusheji.service.SignUpService;
import com.chengxusheji.po.SignUp;
import com.chengxusheji.service.ActivityInfoService;
import com.chengxusheji.po.ActivityInfo;
import com.chengxusheji.service.UserInfoService;
import com.chengxusheji.po.UserInfo;

//SignUp管理控制层
@Controller
@RequestMapping("/SignUp")
public class SignUpController extends BaseController {

    /*业务层对象*/
    @Autowired
    public SignUpService signUpService;

    @Autowired
    public ActivityInfoService activityInfoService;
    @Resource
    public UserService userService;

    @InitBinder("activityObj")
    public void initBinderactivityObj(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("activityObj.");
    }

    @InitBinder("userObj")
    public void initBinderuserObj(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userObj.");
    }

    @InitBinder("signUp")
    public void initBinderSignUp(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("signUp.");
    }

    /*跳转到添加SignUp视图*/
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) throws Exception {
        model.addAttribute(new SignUp());
        /*查询所有的ActivityInfo信息*/
        return "SignUp_add";
    }

    /*客户端ajax方式提交添加活动报名信息*/
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@Validated SignUp signUp, BindingResult br,
                    Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String message = "";
        boolean success = false;
        if (br.hasErrors()) {
            message = "输入信息不符合要求！";
            writeJsonResponse(response, success, message);
            return;
        }
        signUpService.addSignUp(signUp);
        message = "活动报名添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
    }

    /*ajax方式按照查询条件分页查询活动报名信息*/
    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET, RequestMethod.POST})
    public void list(@ModelAttribute("activityObj") ActivityInfo activityObj, @ModelAttribute("userObj") UserInfo userObj, String signUpTime, Integer page, Integer rows, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (page == null || page == 0) page = 1;
        if (signUpTime == null) signUpTime = "";
        if (rows != 0) signUpService.setRows(rows);
        List<SignUp> signUpList = signUpService.querySignUp(activityObj, userObj, signUpTime, page);
        /*计算总的页数和总的记录数*/
        signUpService.queryTotalPageAndRecordNumber(activityObj, userObj, signUpTime);
        /*获取到总的页码数目*/
        int totalPage = signUpService.getTotalPage();
        /*当前查询条件下总记录数*/
        int recordNumber = signUpService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //将要被返回到客户端的对象
        JSONObject jsonObj = new JSONObject();
        jsonObj.accumulate("total", recordNumber);
        JSONArray jsonArray = new JSONArray();
        for (SignUp signUp : signUpList) {
            JSONObject jsonSignUp = signUp.getJsonObject();
            jsonArray.put(jsonSignUp);
        }
        jsonObj.accumulate("rows", jsonArray);
        out.println(jsonObj.toString());
        out.flush();
        out.close();
    }

    /*ajax方式按照查询条件分页查询活动报名信息*/
    @RequestMapping(value = {"/listAll"}, method = {RequestMethod.GET, RequestMethod.POST})
    public void listAll(HttpServletResponse response) throws Exception {
        List<SignUp> signUpList = signUpService.queryAllSignUp();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONArray jsonArray = new JSONArray();
        for (SignUp signUp : signUpList) {
            JSONObject jsonSignUp = new JSONObject();
            jsonSignUp.accumulate("signId", signUp.getSignId());
            jsonArray.put(jsonSignUp);
        }
        out.println(jsonArray.toString());
        out.flush();
        out.close();
    }

    /*前台按照查询条件分页查询活动报名信息*/
    @RequestMapping(value = {"/frontlist"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String frontlist(@ModelAttribute("activityObj") ActivityInfo activityObj, @ModelAttribute("userObj") UserInfo userObj, String signUpTime, Integer currentPage, Model model, HttpServletRequest request) throws Exception {
        if (currentPage == null || currentPage == 0) currentPage = 1;
        if (signUpTime == null) signUpTime = "";
        List<SignUp> signUpList = signUpService.querySignUp(activityObj, userObj, signUpTime, currentPage);
        /*计算总的页数和总的记录数*/
        signUpService.queryTotalPageAndRecordNumber(activityObj, userObj, signUpTime);
        /*获取到总的页码数目*/
        int totalPage = signUpService.getTotalPage();
        /*当前查询条件下总记录数*/
        int recordNumber = signUpService.getRecordNumber();
        request.setAttribute("signUpList", signUpList);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("recordNumber", recordNumber);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("activityObj", activityObj);
        request.setAttribute("userObj", userObj);
        request.setAttribute("signUpTime", signUpTime);
        List<ActivityInfo> activityInfoList = activityInfoService.queryAllActivityInfo();
        request.setAttribute("activityInfoList", activityInfoList);
        List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
        request.setAttribute("userInfoList", userInfoList);
        return "SignUp/signUp_frontquery_result";
    }

    /*前台查询SignUp信息*/
    @RequestMapping(value = "/{signId}/frontshow", method = RequestMethod.GET)
    public String frontshow(@PathVariable Integer signId, Model model, HttpServletRequest request) throws Exception {
        /*根据主键signId获取SignUp对象*/
        SignUp signUp = signUpService.getSignUp(signId);

        List<ActivityInfo> activityInfoList = activityInfoService.queryAllActivityInfo();
        request.setAttribute("activityInfoList", activityInfoList);
        List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
        request.setAttribute("userInfoList", userInfoList);
        request.setAttribute("signUp", signUp);
        return "SignUp/signUp_frontshow";
    }

    /*ajax方式显示活动报名修改jsp视图页*/
    @RequestMapping(value = "/{signId}/update", method = RequestMethod.GET)
    public void update(@PathVariable Integer signId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*根据主键signId获取SignUp对象*/
        SignUp signUp = signUpService.getSignUp(signId);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //将要被返回到客户端的对象
        JSONObject jsonSignUp = signUp.getJsonObject();
        out.println(jsonSignUp.toString());
        out.flush();
        out.close();
    }

    /*ajax方式更新活动报名信息*/
    @RequestMapping(value = "/{signId}/update", method = RequestMethod.POST)
    public void update(@Validated SignUp signUp, BindingResult br,
                       Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String message = "";
        boolean success = false;
        if (br.hasErrors()) {
            message = "输入的信息有错误！";
            writeJsonResponse(response, success, message);
            return;
        }
        try {
            signUpService.updateSignUp(signUp);
            message = "活动报名更新成功!";
            success = true;
            writeJsonResponse(response, success, message);
        } catch (Exception e) {
            e.printStackTrace();
            message = "活动报名更新失败!";
            writeJsonResponse(response, success, message);
        }
    }

    /*删除活动报名信息*/
    @RequestMapping(value = "/{signId}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Integer signId, HttpServletRequest request) throws UnsupportedEncodingException {
        try {
            signUpService.deleteSignUp(signId);
            request.setAttribute("message", "活动报名删除成功!");
            return "message";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "活动报名删除失败!");
            return "error";

        }

    }

    /*ajax方式删除多条活动报名记录*/
    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    public void delete(String signIds, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        String message = "";
        boolean success = false;
        try {
            int count = signUpService.deleteSignUps(signIds);
            success = true;
            message = count + "条记录删除成功";
            writeJsonResponse(response, success, message);
        } catch (Exception e) {
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
    }

    /*按照查询条件导出活动报名信息到Excel*/
    @RequestMapping(value = {"/OutToExcel"}, method = {RequestMethod.GET, RequestMethod.POST})
    public void OutToExcel(@ModelAttribute("activityObj") ActivityInfo activityObj, @ModelAttribute("userObj") UserInfo userObj, String signUpTime, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (signUpTime == null) signUpTime = "";
        List<SignUp> signUpList = signUpService.querySignUp(activityObj, userObj, signUpTime);
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "SignUp信息记录";
        String[] headers = {"报名id", "报名的活动", "报名人", "报名宣誓", "报名时间"};
        List<String[]> dataset = new ArrayList<String[]>();
        for (int i = 0; i < signUpList.size(); i++) {
            SignUp signUp = signUpList.get(i);
            dataset.add(new String[]{signUp.getSignId() + "", signUp.getActivityObj().getTitle(), signUp.getUserObj().getName(), signUp.getSignUpVow(), signUp.getSignUpTime()});
        }
        /*
        OutputStream out = null;
		try {
			out = new FileOutputStream("C://output.xls");
			ex.exportExcel(title,headers, dataset, out);
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
        OutputStream out = null;//创建一个输出流对象
        try {
            out = response.getOutputStream();//
            response.setHeader("Content-disposition", "attachment; filename=" + "SignUp.xls");//filename是下载的xls的名，建议最好用英文
            response.setContentType("application/msexcel;charset=UTF-8");//设置类型
            response.setHeader("Pragma", "No-cache");//设置头
            response.setHeader("Cache-Control", "no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            ex.exportExcel(rootPath, _title, headers, dataset, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
