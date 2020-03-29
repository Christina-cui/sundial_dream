package com.cuijing.sundial_dream.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.condition.ActivityInfoCondition;
import com.cuijing.sundial_dream.condition.BaseController;
import com.cuijing.sundial_dream.entity.ActivityInfo;
import com.cuijing.sundial_dream.service.ActivityInfoService;
import com.cuijing.sundial_dream.service.ActivityTypeService;
import com.cuijing.sundial_dream.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/ActivityInfo")
public class ActivityInfoController extends BaseController {

    @Autowired
    ActivityInfoService activityInfoService;
    @Autowired
    ActivityTypeService activityTypeService;
    @InitBinder("typeObj")
    public void initBindertypeObj(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("typeObj.");
    }
    @InitBinder("activityInfo")
    public void initBinderActivityInfo(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("activityInfo.");
    }


    @GetMapping(value = "/add")
    public void add(@Validated ActivityInfo activityInfo, BindingResult br, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message = "";
        boolean success = false;
        if(br.hasErrors()){
            message = "输入信息不符合要求！";
            writeJsonResponse(response,success,message);
            return;
        }
        try{
            activityInfo.setActivityPhoto(this.handlePhotoUpload(request,"activityPhotoFile"));
        }catch (UserException ex){
            message = "图片格式不正确";
            writeJsonResponse(response,success,message);
            return;
        }
        activityInfoService.saveActivityInfo(activityInfo);
        message = "活动创建成功";
        success = true;
        writeJsonResponse(response,success,message);
    }

    @RequestMapping("/list")
    public List<ActivityInfo> listPage(ActivityInfoCondition activityInfoCondition, Integer pages, Integer pageSize,HttpServletRequest request,HttpServletResponse response){
        Page<ActivityInfo> page = new Page<>(pages,pageSize);
        IPage<ActivityInfo> infoIPage = activityInfoService.findActivityWithType(activityInfoCondition,page);
        return infoIPage.getRecords();
    }





}
