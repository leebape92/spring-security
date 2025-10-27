package com.example.demo;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.security.CustomUserDetails;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PageController {
	
//	private final SessionRegistry sessionRegistry;
	
    @GetMapping("/login0001")
    public String loginPage(HttpServletRequest request, Model model) {
    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/main";
        }
    	
        // 세션에서 메시지 가져오기
        String errorMessage = (String) request.getSession().getAttribute("loginErrorMessage");
        if (errorMessage != null) {
            model.addAttribute("loginErrorMessage", errorMessage);

            // 한 번 읽은 후 세션에서 제거
            request.getSession().removeAttribute("loginErrorMessage");
        }
        
        // 로그아웃 메시지 처리
        String logoutMessagetest = (String) request.getSession().getAttribute("logoutMessage123");
        System.out.println("logoutMessagetest ::: " + logoutMessagetest);
        
        String logoutMessage = (String) request.getSession().getAttribute("logoutMessage");
        System.out.println("logoutMessage ::: " + logoutMessage);
        if (logoutMessage != null) {
            model.addAttribute("logoutMessage", logoutMessage);
            request.getSession().removeAttribute("logoutMessage"); // ✅ 여기서 삭제
        }
        
        return "login/login0001"; // 
    }
    
    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal CustomUserDetails user, Model model) {
    	System.out.println("/main 컨트롤러**********************8");
        model.addAttribute("username", user.getUsername());
        return "main/main";
    }
}
