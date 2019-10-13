package popteam.cinepic.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import popteam.cinepic.dao.DAO;
import popteam.cinepic.vo.Movie;
import popteam.cinepic.vo.Review;
import popteam.cinepic.vo.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	DAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "main";
	}
	@RequestMapping(value = "/score", method = RequestMethod.POST)
	@ResponseBody
	public double score(Review review) {
		return dao.selectAverage(review);      
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		return "signup";
	}
	
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public String insertUser(User user) {
		if(dao.selectId(user) != null)
			return "signup";
		dao.insertUser(user);
		return "main";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/selectUser", method = RequestMethod.POST)
	public String insertUser(User user, HttpSession session) {
		if (dao.selectUser(user) == null)
			return "login";
		session.setAttribute("loginId", user.getId());
		return "main";
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find() {
		return "find";
	}
	
	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	@ResponseBody
	public String findId(User user) {
		if(dao.findId(user) == null)
			return "No account, please signup";
		return "ID : " + dao.findId(user).getId();
	}
	
	@RequestMapping(value = "/findPw", method = RequestMethod.POST)
	@ResponseBody
	public String findPw(User user) {
		if(dao.findPw(user) == null)
			return "No account, please check your id";
		return "PASSWORD : " + dao.findPw(user).getPw();
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(Model model, HttpSession session) {
		String id = (String)session.getAttribute("loginId");
		User user = new User();
		user.setId(id);
		User loginUser = dao.selectId(user);
		model.addAttribute("user", loginUser);
		return "account";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@ResponseBody
	public String updateUser(User user, HttpSession session) {
		if(dao.updateUser(user) == 0)
			return "Fail";
		session.invalidate();
		return "Update complete";
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(User user, HttpSession session) {
		if(dao.selectUser(user) == null)
			return "Please check your password";
		if(dao.deleteUser(user) == 0)
			return "Fail";
		session.invalidate();
		return "Delete complete";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "main";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<Movie> search(Movie movie) {
		return dao.selectAllMovie(movie);		
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(Movie movie, Model model) {
		Movie info = dao.selectMovie(movie);	
		model.addAttribute("info", info);
		return "info";
	}
	
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<Review> review(Review review) {
		return dao.selectAllReview(review);		
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<Review> upload(Review review) {
		dao.deleteReview(review);
		dao.insertReview(review);
		return dao.selectAllReview(review);	
	}
	
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage() {
		return "mypage";
	}
	
	@RequestMapping(value = "/myreview", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<Review> myreview(Review review) {
		return dao.selectMyReview(review);	
	}
	
	@RequestMapping(value = "/deleteReview", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<Review> deleteReview(Review review) {
		System.out.println(review);
		dao.deleteReview(review);
		return dao.selectAllReview(review);	
	}
}
