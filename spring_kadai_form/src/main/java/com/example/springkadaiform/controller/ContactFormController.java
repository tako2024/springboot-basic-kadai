package com.example.springkadaiform.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {

	@GetMapping("/form")
	public String contactViewPage(Model model) {

		// すでにインスタンスが存在する場合は行わない
		if (!model.containsAttribute("contactForm")) {
		    // ビューにフォームクラスのインスタンスを渡す
		    model.addAttribute("contactForm", new ContactForm());
		}
		
		return "contactFormView";
	}

	@PostMapping("/confirm")
	public String confirmViewPage(RedirectAttributes redirectAttributes, @Validated ContactForm form,
													BindingResult result,Model model) {
		
		//入力エラーがある場合には、入力viewを再表示する
		if(result.hasErrors()) {
			// フォームクラスをビューに受け渡す
			redirectAttributes.addFlashAttribute("contactForm",form);

            // バリデーション結果をビューに受け渡す
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX
                    + Conventions.getVariableName(form), result);

			return "redirect:/form";
		}
		
		//フォーム内容をViewへ渡して、確認表示に利用する
		model.addAttribute("contactForm", form);
		
		//入力エラー無しなので、確認viewを表示する
		return "confirmView";
	}
}
