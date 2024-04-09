package project.keyappsk.global.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class ErrorControllerImpl implements ErrorController{
    @RequestMapping("/error")
    public String handlerError(HttpServletRequest request, Model model) {
        Object errorCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        // 예외 객체를 받아와서 메시지를 추출합니다.
//        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
//        String errorMessageFromException = throwable.getMessage();
//        log.info("errorMessageFromException: {}", errorMessageFromException);

        log.info("errorCode {}", errorCode);
        if(errorCode != null) model.addAttribute("errorCode", errorCode.toString());
//        if(errorMessage != null) model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }
}
