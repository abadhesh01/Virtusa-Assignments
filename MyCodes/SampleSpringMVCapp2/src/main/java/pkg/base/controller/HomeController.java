package pkg.base.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pkg.base.model.Contact;

@Controller
public class HomeController {

	private final Logger logger;
	private List<Contact> contacts;
	private int count = 0;

	// Returning a sample JSP page.
	@RequestMapping(path = "/index", method = RequestMethod.GET)
	public String getIndexPage() {
		return "index"; // Returns "index.jsp".
	}

	// Returning a sample JSP page with custom message.
	@RequestMapping(path = "/index/message", method = RequestMethod.GET)
	public String getIndexPageWithCustomMessage(HttpSession session) {
		session.setAttribute("message", "Sample Custom Message");
		return "index";
	}

	// Returning a sample JSP page with sample contact details.
	@RequestMapping(path = "/index/contact", method = RequestMethod.GET)
	public String getIndexPageWithSampleContact(Model model) {
		Contact sampleContact = new Contact("Lorem Ipsium", "0123456789", "ipsium.lorem@email.com");
		sampleContact.setId(ThreadLocalRandom.current().nextLong(1, 1000));
		model.addAttribute("contact", sampleContact);
		return "index";
	}

	// Clearing JSP attributes' values.
	@RequestMapping(path = "/index/clear", method = RequestMethod.GET)
	public String clearIndexPage(HttpSession session, Model model) {
		session.setAttribute("message", null);
		model.addAttribute("contact", null);
		return "index";
	}

	// Returning a sample response body.
	@RequestMapping(path = "/body", method = RequestMethod.GET)
	@ResponseBody
	public String getSampleResponse() {
		return "<h1 style=\"color:Olive; text-align:center\">Sample JSP Page</h1>";
	}

	// Getting contact form.
	@RequestMapping(path = "/contactform", method = RequestMethod.GET)
	public String getContactForm() {
		return "form";
	}

	// Posting contact details.
	@RequestMapping(value = "/postcontact", method = RequestMethod.POST, params = { "name", "phone", "email" })
	@ResponseBody
	public String postContactDetails(@RequestParam(name = "name", defaultValue = "N/A", required = true) String name,
			@RequestParam(name = "phone", defaultValue = "N/A", required = true) String phone,
			@RequestParam(name = "email", defaultValue = "N/A", required = true) String email) {
		if (name == "") {
			name = "N/A";
		}
		if (phone == "") {
			phone = "N/A";
		}
		if (email == "") {
			email = "N/A";
		}
		Contact contact = new Contact(name, phone, email);
		contact.setId(++count);
		contacts.add(contact);
		logger.trace("\n\n\n\n" + contact + "\n\n\n");
		return "<section style=\"text-align: center; font-size: 20px; color: #CE0CF0\"><strong>" + contact.toString()
				+ "</strong></section>";
	}

	// Getting contact form model.
	@RequestMapping(path = "/contactformmodel", method = RequestMethod.GET)
	public String getContactFormModel() {
		return "form_model";
	}

	// Posting contact details in model format.
	@RequestMapping(value = "/postcontactmodel", method = RequestMethod.POST, params = { "name", "phone", "email" })
	@ResponseBody
	public String postContactDetailsInModel(@ModelAttribute Contact contact) {
		contact.setId(++count);
		if (contact.getName() == "") {
			contact.setName("Lorem Ipsium [" + count + "]");
		}
		if (contact.getPhone() == "") {
			contact.setPhone(generateMobileNumber());
		}
		if (contact.getEmail() == "") {
			contact.setEmail("ipsium.lorem[" + count + "]@email.com");
		}
		contacts.add(contact);
		logger.trace("\n\n\n\n" + contact + "\n\n\n");
		return "<section style=\"text-align: center; font-size: 20px; color: #CE0CF0\"><strong>" + contact.toString()
				+ "</strong></section>";
	}

	// Getting all contacts.
	@RequestMapping(path = "/contacts")
	@ResponseBody
	public String getContactList() {
		if (contacts.isEmpty()) {
			return "<section style=\"text-align: center; font-size: 20px; color: #CE0CF0\"><strong>"
					+ "Contact List is Empty! :(" + "</strong></section>";
		}
		String text = "<section style=\"text-align: center; font-size: 20px; color: #CE0CF0\"><strong><br>\n";
		for (Contact contact : contacts) {
			text += "<a href=\"http://localhost:9090/SampleSpringMVCapp2/contacts/" + contact.getId() + "\">" + contact
					+ "</a><br>\n";
		}
		text += "</strong></section>";
		logger.trace("\n\n\n\n" + text + "\n\n\n");
		return text;
	}

	// Getting a contact by id.
	@RequestMapping(path = "/contacts/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getContactById(@PathVariable("id") long id) {
		String text = "<section style=\"text-align: center; font-size: 20px; color: #CE0CF0\"><strong>";
		Contact contact = null;
		for (Contact temporaryContact : contacts) {
			if (temporaryContact.getId() == id) {
				contact = temporaryContact;
				break;
			}
		}
		if (contact == null) {
			text += "Contact with id=\"" + id + "\" was not found! :(";
		} else {
			text += contact;
		}
	    text += "</strong></section>";
		return text;
	}

	public String generateMobileNumber() {
		String mobileNumber = "";
		for (int counter = 0; counter < 10; counter++) {
			mobileNumber += ThreadLocalRandom.current().nextInt(0, 10);
		}
		return mobileNumber;
	}

	public HomeController() {
		super();
		contacts = new ArrayList<Contact>();
		logger = Logger.getLogger(HomeController.class);
		BasicConfigurator.configure();
		logger.setLevel(Level.ALL);
		logger.trace("\n\n\n\n\"HomeController\" instance created successfully. :)\n\n\n");
	}

}
