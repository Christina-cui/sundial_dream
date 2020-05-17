package com.cuijing.sundial_dream.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuijing.sundial_dream.converter.ActivityInfoConverter;
import com.cuijing.sundial_dream.dto.ActivityInfoVo;
import com.cuijing.sundial_dream.entity.ActivityInfo;
import com.cuijing.sundial_dream.entity.ActivityType;
import com.cuijing.sundial_dream.service.ActivityInfoService;
import com.cuijing.sundial_dream.service.ActivityTypeService;
import com.cuijing.sundial_dream.utils.UserException;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ActivityInfo")
public class ActivityInfoController extends BaseController {
    @Autowired
    public ActivityInfoService activityInfoService;

    @Autowired
    public ActivityTypeService activityTypeService;

    @Autowired
    public ActivityInfoConverter activityInfoConverter;

    @InitBinder("typeObj")
    public void initBindertypeObj(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("typeObj.");
    }

    @InitBinder("activityInfo")
    public void initBinderActivityInfo(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("activityInfo.");
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) throws Exception {
//        List<ActivityInfo> list = activityInfoService.list();
//        List<ActivityInfoVo> vos = new ArrayList<>();
//        list.forEach(v->{
//            ActivityInfoVo vo = activityInfoConverter.detail(v);
//            vo.setType(activityTypeService.findActivityTypeById(v.getTypeId()).get());
//            vos.add(vo);
//        });
        List<ActivityType> activityTypes = activityTypeService.findAllType();
        model.addAttribute(new ActivityInfoVo());
        request.setAttribute("activityTypeList",activityTypes);
        return "ActivityInfo_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@Validated ActivityInfo activityInfo, BindingResult br,
                    Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String message = "";
        boolean success = false;
        if(br.hasErrors()){
            message = "信息填写有误，请仔细查看！";
            writeJsonResponse(response,success,message);
            return;
        }
        try {
            activityInfo.setActivityPhoto(this.handlePhotoUpload(request, "activityPhotoFile"));
        } catch (UserException ex) {
            message = "图片格式不正确！";
            writeJsonResponse(response, success, message);
            return;
        }
        if (activityInfoService.saveActivityInfo(activityInfo)){
            message = "公益活动添加成功!";
            success = true;
        }else{
            message = "活动已存在,换个名字吧！";
            return;
        }

        writeJsonResponse(response, success, message);

    }


    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET, RequestMethod.POST})
    public void list(@ModelAttribute("typeObj") ActivityType typeObj, String title, Integer page, Integer rows, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Page<ActivityInfo> pagea = new Page(page,rows);


    }


}
