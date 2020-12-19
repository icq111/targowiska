package pl.minicode.targowiska.fileupload;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class FileUploadExceptionAdvice {
    
	@ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleError(MaxUploadSizeExceededException e, RedirectAttributes redirectAttributes) {
		String s = (String) RequestContextHolder.currentRequestAttributes().getAttribute("javax.servlet.forward.request_uri", 0);
		if(s.contains("save")) {
			String temp = s;
			s = temp.replace("save", "create");
		}
        redirectAttributes.addFlashAttribute("file_upload_error", "Dopuszczalna wielkość pliku to 180 KB !");
        return "redirect:"+s;

    }
}