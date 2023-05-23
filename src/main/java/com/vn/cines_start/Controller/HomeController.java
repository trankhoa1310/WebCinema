	package com.vn.cines_start.Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.vn.cines_start.DTO.QRDetailsPOJO;
import com.vn.cines_start.DTO.UserDTO;
import com.vn.cines_start.Model.BillUser;
import com.vn.cines_start.Model.Movie;
import com.vn.cines_start.Model.Room;
import com.vn.cines_start.Model.Schedule;
import com.vn.cines_start.Model.Seat;
import com.vn.cines_start.Model.Ticket;
import com.vn.cines_start.Model.User;
import com.vn.cines_start.Repository.UserRepository;
import com.vn.cines_start.Service.IMovieService;
import com.vn.cines_start.Service.IUserService;
import com.vn.cines_start.Service.Iplm.BillUserImplement;
import com.vn.cines_start.Service.Iplm.MovieImplement;
import com.vn.cines_start.Service.Iplm.PaypalService;
import com.vn.cines_start.Service.Iplm.RoomImplement;
import com.vn.cines_start.Service.Iplm.ScheduleImplement;
import com.vn.cines_start.Service.Iplm.SeatImplement;

import com.vn.cines_start.Service.Iplm.TicketImplement;
import com.vn.cines_start.Service.Iplm.UserImplement;
import com.vn.cines_start.config.PaypalPaymentIntent;
import com.vn.cines_start.config.PaypalPaymentMethod;
import com.vn.cines_start.config.Utils;
import com.vn.cines_start.utils.SecurityUtils;

@Controller
public class HomeController {
	@Autowired
	private MovieImplement movieImplement;

	@Autowired
	private ScheduleImplement scheduleImplement;

	@Autowired
	private SeatImplement seatImplement;

	@Autowired
	private UserImplement userImplement;

	@Autowired
	private TicketImplement ticketImplement;

	@Autowired
	private RoomImplement roomImplement;

	@Autowired
	private BillUserImplement billUserImplement;
	/*
	 * @Autowired private TicketDetailImplement ticketDetailImplement;
	 */

	@GetMapping({ "/", "/home" })
	public String home(Model model, @Param("id") Long id,
			@Param("showDate") @DateTimeFormat(iso = ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd" }) Date showDate,
			@Param("showTime") Time showTime) throws ParseException {

		model.addAttribute("movie", movieImplement.findAll());
		
		/*
		 * model.addAttribute("currentMovie", movieImplement.findMovieByStatus(true));
		 * model.addAttribute("upcomingMovie", movieImplement.findMovieByStatus(false));
		 */
		

		 model.addAttribute("currentMovie", movieImplement.findAllByStatusAndDeleted(true,true));
		 model.addAttribute("upcomingMovie", movieImplement.findAllByStatusAndDeleted(false,true));
		 
		
		
		
		
		model.addAttribute("schedule", scheduleImplement.findAll());

		model.addAttribute("id", id);

		if (id != null) {
			model.addAttribute("scheduleByMovie", scheduleImplement.findScheduleByMovieId((long) id));
			List<Date> dates = (List) model.getAttribute("scheduleByMovie");
			List<String> dateStringList = new ArrayList<>();

			for (Date date : dates) {
				LocalDate d= Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				if(d.isAfter(LocalDate.now()) || d.isEqual(LocalDate.now()))
				{
					String dateStr = String.valueOf(date);
					dateStringList.add(dateStr);
				}
				
			}
			model.addAttribute("scheduleByMovieString", dateStringList);
		}
		if (id != null && showDate != null) {
			String pattern = "yyyy-MM-dd";

			// Create an instance of SimpleDateFormat used for formatting
			// the string representation of date according to the chosen pattern
			SimpleDateFormat df = new SimpleDateFormat(pattern);

			// Get the today date using Calendar object.
			// Using DateFormat format method we can create a string
			// representation of a date with the defined format.
			String todayAsString = df.format(showDate);
			model.addAttribute("showDate", todayAsString);
			
			model.addAttribute("scheduleByMovieAndByDate",
					scheduleImplement.findAllByShowTime((long) id, new java.sql.Date(showDate.getTime())));
			List<Time> Times = (List) model.getAttribute("scheduleByMovieAndByDate");
			List<String> dateStringTimeList = new ArrayList<>();
			String today = df.format(Calendar.getInstance().getTime());
			if(LocalDate.parse(todayAsString).isEqual(LocalDate.now()))
			{
				for(Time time : Times)
				{
					LocalTime timenow = time.toLocalTime();
					if(timenow.isAfter(LocalTime.now()))
					{
						String dateStr = String.valueOf(time);
						dateStringTimeList.add(dateStr);
					}
				}
			}
			else {
				for (Time time : Times) {
					
					String dateStr = String.valueOf(time);
					dateStringTimeList.add(dateStr);
				}
			}
			
			model.addAttribute("scheduleByMovieAndByDateString", dateStringTimeList);
			System.out.println(model.getAttribute("scheduleByMovieAndByDateString"));

		}
		if (id != null && showDate != null && showTime != null) {
			String pattern1 = "HH:mm:ss";
			SimpleDateFormat dff = new SimpleDateFormat(pattern1);
			String todayAsString1 = dff.format(showTime);
			model.addAttribute("showTime", todayAsString1);
			model.addAttribute("roomid", scheduleImplement.findRoomByMovieAndDateTime((long) id,
					new java.sql.Date(showDate.getTime()), (Time) showTime));

			/*
			 * return
			 * "redirect:/bookingTicket?movieId={id}&roomId={roomid}&showDate={showDate}&showTime={showTime}";
			 */
		}
		return "index";
	}

	@GetMapping("currentMovie")
	public String currentMovie(Model model) {
		/*
		 * model.addAttribute("currentMovie", movieImplement.findMovieByStatus(true));
		 */
		
		 model.addAttribute("currentMovie", movieImplement.findAllByStatusAndDeleted(true,true));
		
		
		return "phimDangChieu";
	}

	@GetMapping("upcomingMovie")
	public String upcomingMovie(Model model) {
		/*
		 * model.addAttribute("upcomingMovie", movieImplement.findMovieByStatus(false));
		 */
		 model.addAttribute("upcomingMovie", movieImplement.findAllByStatusAndDeleted(false,true));
		return "phimSapChieu";
	}

	@GetMapping("schedule")
	public String showSchedule(Model model) throws ParseException {
		List<String> dateStringList = new ArrayList<>();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String sdate = simpleDateFormat.format(new Date());
		Calendar c = Calendar.getInstance();
		c.setTime(simpleDateFormat.parse(sdate));
//		for(int i=0;i<7;i++)
//		{
//			dateStringList.add(simpleDateFormat.format(c.getTime()));
//			c.add(Calendar.DATE, 1);  // number of days to add
//			
//		}
		c.add(Calendar.DATE, 1); // number of days to add
		String sdate1 = simpleDateFormat.format(c.getTime());
		c.add(Calendar.DATE, 1); // number of days to add
		String sdate2 = simpleDateFormat.format(c.getTime());
		c.add(Calendar.DATE, 1); // number of days to add
		String sdate3 = simpleDateFormat.format(c.getTime());
		c.add(Calendar.DATE, 1); // number of days to add
		String sdate4 = simpleDateFormat.format(c.getTime());
		c.add(Calendar.DATE, 1); // number of days to add
		String sdate5 = simpleDateFormat.format(c.getTime());
		c.add(Calendar.DATE, 1); // number of days to add
		String sdate6 = simpleDateFormat.format(c.getTime());
		model.addAttribute("currentDate", sdate);
		model.addAttribute("currentDate1", sdate1);
		model.addAttribute("currentDate2", sdate2);
		model.addAttribute("currentDate3", sdate3);
		model.addAttribute("currentDate4", sdate4);
		model.addAttribute("currentDate5", sdate5);
		model.addAttribute("currentDate6", sdate6);
		Date date = simpleDateFormat.parse(sdate);
		Date date1 = simpleDateFormat.parse(sdate1);
		Date date2 = simpleDateFormat.parse(sdate2);
		Date date3 = simpleDateFormat.parse(sdate3);
		Date date4 = simpleDateFormat.parse(sdate4);
		Date date5 = simpleDateFormat.parse(sdate5);
		Date date6 = simpleDateFormat.parse(sdate6);

		model.addAttribute("time", LocalTime.now());
		List<Movie> listMovie = new ArrayList<>();
		// model.addAttribute("scheduleByDate", scheduleImplement.findAllByShowDate(new
		// java.sql.Date(date.getTime())));
		List<Long> schedules = scheduleImplement.findAllByShowDate(new java.sql.Date(date.getTime()));
		List<Time> scheduleList = new ArrayList<>();
		for (Long schedule : schedules) {
			if (schedule != null) {
				Optional<Movie> mv = movieImplement.findMovieById((long) schedule);
				mv.ifPresent(newmv -> listMovie.add(newmv));
			}
		}
		model.addAttribute("schedules", scheduleImplement.findAllByShowDate1(new java.sql.Date(date.getTime())));
		List<Movie> lstnew= new ArrayList<>();
		for (Movie movie : listMovie) {
			if(movie.isDeleted())
			{
				lstnew.add(movie);
			}
		}
		model.addAttribute("listMovie", lstnew);

		List<Movie> listMovie1 = new ArrayList<>();

		// model.addAttribute("scheduleByDate", scheduleImplement.findAllByShowDate(new
		// java.sql.Date(date.getTime())));
		List<Long> schedules1 = scheduleImplement.findAllByShowDate(new java.sql.Date(date1.getTime()));
		for (Long schedule : schedules1) {
			if (schedule != null) {
				Optional<Movie> mv = movieImplement.findMovieById((long) schedule);
				mv.ifPresent(newmv -> listMovie1.add(newmv));
			}
		}
		model.addAttribute("schedules1", scheduleImplement.findAllByShowDate1(new java.sql.Date(date1.getTime())));
		List<Movie> lstnew1= new ArrayList<>();
		for (Movie movie : listMovie1) {
			if(movie.isDeleted())
			{
				lstnew1.add(movie);
			}
		}
		model.addAttribute("listMovie1", lstnew1);

		List<Movie> listMovie2 = new ArrayList<>();

		// model.addAttribute("scheduleByDate", scheduleImplement.findAllByShowDate(new
		// java.sql.Date(date.getTime())));
		List<Long> schedules2 = scheduleImplement.findAllByShowDate(new java.sql.Date(date2.getTime()));
		for (Long schedule : schedules2) {
			if (schedule != null) {
				Optional<Movie> mv = movieImplement.findMovieById((long) schedule);
				mv.ifPresent(newmv -> listMovie2.add(newmv));
			}
		}
		model.addAttribute("schedules2", scheduleImplement.findAllByShowDate1(new java.sql.Date(date2.getTime())));
		List<Movie> lstnew2= new ArrayList<>();
		for (Movie movie : listMovie2) {
			if(movie.isDeleted())
			{
				lstnew2.add(movie);
			}
		}
		model.addAttribute("listMovie2", lstnew2);

		List<Movie> listMovie3 = new ArrayList<>();

		// model.addAttribute("scheduleByDate", scheduleImplement.findAllByShowDate(new
		// java.sql.Date(date.getTime())));
		List<Long> schedules3 = scheduleImplement.findAllByShowDate(new java.sql.Date(date3.getTime()));
		System.out.println(new java.sql.Date(date3.getTime()));
		for (Long schedule : schedules3) {
			if (schedule != null) {
				System.out.println("aa");
				Optional<Movie> mv = movieImplement.findMovieById((long) schedule);
				mv.ifPresent(newmv -> listMovie3.add(newmv));
			}
		}
		model.addAttribute("schedules3", scheduleImplement.findAllByShowDate1(new java.sql.Date(date3.getTime())));
		List<Movie> lstnew3= new ArrayList<>();
		for (Movie movie : listMovie3) {
			if(movie.isDeleted())
			{
				lstnew3.add(movie);
			}
		}
		model.addAttribute("listMovie3", lstnew3);

		List<Movie> listMovie4 = new ArrayList<>();

		// model.addAttribute("scheduleByDate", scheduleImplement.findAllByShowDate(new
		// java.sql.Date(date.getTime())));
		List<Long> schedules4 = scheduleImplement.findAllByShowDate(new java.sql.Date(date4.getTime()));
		for (Long schedule : schedules4) {
			if (schedule != null) {
				Optional<Movie> mv = movieImplement.findMovieById((long) schedule);
				mv.ifPresent(newmv -> listMovie4.add(newmv));
			}
		}
		model.addAttribute("schedules4", scheduleImplement.findAllByShowDate1(new java.sql.Date(date4.getTime())));
		List<Movie> lstnew4= new ArrayList<>();
		for (Movie movie : listMovie4) {
			if(movie.isDeleted())
			{
				lstnew4.add(movie);
			}
		}
		model.addAttribute("listMovie4", lstnew4);

		List<Movie> listMovie5 = new ArrayList<>();

		// model.addAttribute("scheduleByDate", scheduleImplement.findAllByShowDate(new
		// java.sql.Date(date.getTime())));
		List<Long> schedules5 = scheduleImplement.findAllByShowDate(new java.sql.Date(date5.getTime()));
		for (Long schedule : schedules5) {
			if (schedule != null) {
				Optional<Movie> mv = movieImplement.findMovieById((long) schedule);
				mv.ifPresent(newmv -> listMovie5.add(newmv));
			}
		}
		model.addAttribute("schedules5", scheduleImplement.findAllByShowDate1(new java.sql.Date(date5.getTime())));
		List<Movie> lstnew5= new ArrayList<>();
		for (Movie movie : listMovie5) {
			if(movie.isDeleted())
			{
				lstnew5.add(movie);
			}
		}
		model.addAttribute("listMovie5", lstnew5);

		List<Movie> listMovie6 = new ArrayList<>();

		// model.addAttribute("scheduleByDate", scheduleImplement.findAllByShowDate(new
		// java.sql.Date(date.getTime())));
		List<Long> schedules6 = scheduleImplement.findAllByShowDate(new java.sql.Date(date6.getTime()));
		for (Long schedule : schedules6) {
			if (schedule != null) {
				Optional<Movie> mv = movieImplement.findMovieById((long) schedule);
				mv.ifPresent(newmv -> listMovie6.add(newmv));
			}
		}
		model.addAttribute("schedules6", scheduleImplement.findAllByShowDate1(new java.sql.Date(date6.getTime())));
		List<Movie> lstnew6= new ArrayList<>();
		for (Movie movie : listMovie6) {
			if(movie.isDeleted())
			{
				lstnew6.add(movie);
			}
		}
		model.addAttribute("listMovie6", lstnew6);

//		model.addAttribute("scheduleByDate1", scheduleImplement.findAllByShowDate(new java.sql.Date(date1.getTime())));
//		model.addAttribute("scheduleByDate2", scheduleImplement.findAllByShowDate(new java.sql.Date(date2.getTime())));
//		model.addAttribute("scheduleByDate3", scheduleImplement.findAllByShowDate(new java.sql.Date(date3.getTime())));
//		model.addAttribute("scheduleByDate4", scheduleImplement.findAllByShowDate(new java.sql.Date(date4.getTime())));
//		model.addAttribute("scheduleByDate5", scheduleImplement.findAllByShowDate(new java.sql.Date(date5.getTime())));
//		model.addAttribute("scheduleByDate6", scheduleImplement.findAllByShowDate(new java.sql.Date(date6.getTime())));
		return "lichChieu";
	}

	@GetMapping("movieDetail")
	public String showMovieDetail(Model model, @Param("id") Long id) {
		model.addAttribute("id", id);
		Optional<Movie> mv = movieImplement.findMovieById((long) id);
		mv.ifPresent(newmv -> {
			System.out.println("ton tai");
			model.addAttribute("movieDetailObj", newmv);
			System.out.println(newmv);
		});
		System.out.println(model.getAttribute("movieDetailObj"));
		model.addAttribute("scheduleByMovie", scheduleImplement.findScheduleByMovieId((long) id));
		List<java.sql.Date> dates = (List) model.getAttribute("scheduleByMovie");
		List<LocalDate> dateStringList = new ArrayList<>();

		for (java.sql.Date date : dates) {
			LocalDate dateStr = date.toLocalDate();
			if(dateStr.isEqual(LocalDate.now())||dateStr.isAfter(LocalDate.now()))
			{
				dateStringList.add(dateStr);
			}
			
		}
		model.addAttribute("time", LocalTime.now());
		model.addAttribute("date", LocalDate.now());
		model.addAttribute("scheduleByMovie1", scheduleImplement.findAllScheduleByMovieId1((long) id));
		// System.out.println(scheduleImplement.findAllScheduleByMovieId1((long) id));
		model.addAttribute("scheduleByMovieString", dateStringList);

		return "chiTietPhim";
	}

	@GetMapping("bookingTicket")
	public String bookingTicket(Model model, @Param("movieId") Long movieId, @Param("roomId") Long roomId,
			@Param("showDate") @DateTimeFormat(iso = ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd" }) Date showDate,
			@Param("showTime") String showTime, RedirectAttributes attributes) {
		model.addAttribute("movieId", movieId);
		model.addAttribute("roomId", roomId);
		model.addAttribute("showDate", showDate);
		model.addAttribute("showTime", showTime);
		model.addAttribute("seatRow", seatImplement.findAllRowSeatsByRoomId((long) roomId));
		
		List<Seat> seatAll = seatImplement.findAllSeatsByRoomId((long) roomId);

		for (Seat s : seatAll) {
			s.setStatus(true);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlStartDate = java.sql.Date.valueOf(sdf.format(showDate));

		Schedule sc = scheduleImplement.showScheduleID(movieId, roomId, sqlStartDate,
				Time.valueOf(LocalTime.parse(showTime)));

		List<Long> listSeatId = new ArrayList<>();

		listSeatId = ticketImplement.showListSeatId(sc.getId());

		for (Seat s : seatAll) {
			for (Long seatId : listSeatId) {
				if (s.getId() == seatId) {
					s.setStatus(false);
				}
			}

		}
		model.addAttribute("seatAll", seatAll);
		return "chonGhe";

	}

	@GetMapping("billInfor")
	public String billInfor(Model model, @Param("movieId") Long movieId, @Param("roomId") Long roomId,
			@Param("showDate") @DateTimeFormat(iso = ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd" }) Date showDate,
			@Param("showTime") String showTime, @Param("seatList") String seatList, RedirectAttributes attributes) {
		model.addAttribute("movieId", movieId);
		model.addAttribute("roomId", roomId);
		model.addAttribute("showDate", showDate);
		model.addAttribute("showTime", showTime);
		model.addAttribute("seatList", seatList);

		String[] seatLists = seatList.split(",");
		List<Seat> listSeat = new ArrayList<>();

		for (String a : seatLists) {
			Long k = Long.parseLong(a);
			System.out.println(k);
			Optional<Seat> seat = seatImplement.findSeatById(k);
			seat.ifPresent(newseat -> listSeat.add(newseat));

		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlStartDate = java.sql.Date.valueOf(sdf.format(showDate));

		Schedule sc = scheduleImplement.showScheduleID(movieId, roomId, sqlStartDate,
				Time.valueOf(LocalTime.parse(showTime)));

		List<Long> listSeatId = new ArrayList<>();

		listSeatId = ticketImplement.showListSeatId(sc.getId());

		for (Seat s : listSeat) {
			for (Long seatId : listSeatId) {
				if (s.getId() == seatId) {
					attributes.addFlashAttribute("Fail", "Ghe da duoc chon, vui long chon ghe khac!");
					return "redirect:/bookingTicket?movieId=" + movieId + "&roomId=" + roomId + "&showDate="
							+ sdf.format(showDate) + "&showTime=" + showTime;
				}
			}

		}

		Optional<Movie> mv = movieImplement.findMovieById(movieId);
		mv.ifPresent(newmv -> model.addAttribute("nameMovie", newmv.getName()));
		UserDTO result = new UserDTO();
		result.setEmail(SecurityUtils.getPrincipal().getEmail());
		result.setId(SecurityUtils.getPrincipal().getId());
		result.setPhone(SecurityUtils.getPrincipal().getPhone());
		result.setFirstName(SecurityUtils.getPrincipal().getFirstName());
		result.setLastName(SecurityUtils.getPrincipal().getLastName());	
		model.addAttribute("result", result);
		
		
		
		return "thanhToan";
	}

	
	
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private PaypalService paypalService;
	
	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay(){
		return "cancel";
	}
	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				return "success";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
	@GetMapping("/pay")
	public String pay(HttpServletRequest request,@RequestParam("price") double price ){
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(
					price,
					"USD",
					PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale,
					"payment description",
					cancelUrl,
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
	
	
	
	@GetMapping("billInsert")
	public String billInsert(Model model, @Param("movieId") Long movieId, @Param("roomId") Long roomId,
			@Param("showDate") @DateTimeFormat(iso = ISO.DATE_TIME, fallbackPatterns = { "yyyy-MM-dd" }) Date showDate,
			@Param("showTime") String showTime, @Param("seatList") String seatList,RedirectAttributes attributes) {

		String[] seatLists = seatList.split(",");
		List<Seat> listSeat = new ArrayList<>();
		
		for (String a : seatLists) {
			Long k = Long.parseLong(a);
			System.out.println(k);
			Optional<Seat> seat = seatImplement.findSeatById(k);
			seat.ifPresent(newseat -> listSeat.add(newseat));

		}
		Long total = (long) (listSeat.size()*45000);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		java.sql.Date sqlStartDate = java.sql.Date.valueOf(sdf.format(showDate));

		Schedule sc = scheduleImplement.showScheduleID(movieId, roomId, sqlStartDate,
				Time.valueOf(LocalTime.parse(showTime)));

		List<Long> listSeatId = new ArrayList<>();

		listSeatId = ticketImplement.showListSeatId(sc.getId());
		System.out.println(sc);
		System.out.println(listSeatId);
		for (Seat s : listSeat) {
			for (Long seatId : listSeatId) {
				if (s.getId() == seatId) {
					System.out.println("hello");
					attributes.addFlashAttribute("Fail", "Ghế đã được chọn, vui lòng chọn lại ghế!");
					return "redirect:/bookingTicket?movieId=" + movieId + "&roomId=" + roomId + "&showDate="
							+ sdf.format(showDate) + "&showTime=" + showTime;
				}
			}

		}
		String seatBill = "";
		Ticket newticket = new Ticket();
		for (Seat x : listSeat) {
			seatBill += x.getCategoryName() + " ";
		}
		
		newticket.setSchedules(scheduleImplement.showScheduleID(movieId, roomId, sqlStartDate,
				Time.valueOf(LocalTime.parse(showTime))));

		User newUser = userImplement.findAllByEmail(SecurityUtils.getPrincipal().getEmail());
		newticket.setUsers(newUser);
		newticket.setCreatedAt(LocalDate.now());
		/*
		 * List<Seat> listSeat = null; System.out.println(seatList); for (String a :
		 * seatList) { Long k=Long.parseLong(a); Optional<Seat> seat =
		 * seatImplement.findSeatById(k); seat.ifPresent(newseat ->
		 * listSeat.add(newseat) ); }
		 */
		newticket.setSeats(listSeat);
		newticket.setPrice(total);
		BillUser billUser = new BillUser();
		billUser.setCreatedAt(LocalDate.now());
		billUser.setFirstName(newUser.getFirstName());
		billUser.setLastName(newUser.getLastName());
		Optional<Movie> mvBill = movieImplement.findMovieById(movieId);
		mvBill.ifPresent(newBill -> billUser.setMovieName(newBill.getName()));
		Optional<Room> rBill = roomImplement.findRoomById(roomId);
		rBill.ifPresent(newBill -> billUser.setRoomName(newBill.getName()));

		billUser.setShowDate(java.time.LocalDate.parse(sdf.format(showDate)));
		billUser.setShowTime(LocalTime.parse(showTime));
		billUser.setSeatList(seatBill);
		billUser.setUsers(newUser);
		billUser.setPrice(total);
		
		ticketImplement.create(newticket);
		billUserImplement.create(billUser);
		String urlQR="";
		/* QR */
		try {
			BufferedImage bufferedImage = generateQRCodeImage(billUser);
			File outputfile = new File(
					"D:\\Web_DoAn\\Cines_Start\\src\\main\\resources\\static\\img\\QR-"
					+ billUser.getId() + ".jpg");
			ImageIO.write(bufferedImage, "jpg", outputfile);

			model.addAttribute("qr", billUser);
			urlQR="/img/QR-"+ billUser.getId() + ".jpg";
		} catch (Exception e) {
			e.getMessage();
		}
		
		/* QR */
		Optional<BillUser> opBillUser = billUserImplement.findBillUserById(billUser.getId());
		if (opBillUser.isPresent()) {
			BillUser billQR= opBillUser.get();
			billQR.setUrlQR(urlQR);
			billUserImplement.create(billUser);
		}
		
		
		model.addAttribute("seatList", seatList);
		
		double price1= total;
		model.addAttribute("price",price1);
		return "redirect:/pay?price="+price1;

	}

	@GetMapping("billCancel")
	public String billCancel(Model model, @Param("seatList") String seatList) {
		String[] seatLists = seatList.split(",");
		List<Seat> listSeat = new ArrayList<>();

		for (String a : seatLists) {
			Long k = Long.parseLong(a);
			System.out.println(k);
			Optional<Seat> seat = seatImplement.findSeatById(k);
			seat.ifPresent(newseat -> listSeat.add(newseat));
		}
		for (Seat s : listSeat) {
			s.setStatus(true);
			seatImplement.create(s);
		}

		return "redirect:/";
	}

	@GetMapping("billUser")
	public String billUser(Model model) {
		User newUser = userImplement.findAllByEmail(SecurityUtils.getPrincipal().getEmail());
		List<BillUser> listBill = billUserImplement.findBillByUserId(newUser.getId());
		model.addAttribute("billUsers", listBill);
		return "billUser";
	}

	/*
	 * @GetMapping("/api/user/information") public UserDTO getUserInformation() {
	 * UserDTO result = new UserDTO();
	 * result.setEmail(SecurityUtils.getPrincipal().getEmail());
	 * result.setId(SecurityUtils.getPrincipal().getId());
	 * result.setPhone(SecurityUtils.getPrincipal().getPhone());
	 * result.setFirstName(SecurityUtils.getPrincipal().getFirstName()); return
	 * result; }
	 */

	/*
	 * @PostMapping public String genrateQRCode(@ModelAttribute("qr") QRDetailsPOJO
	 * qrDetailsPOJO, Model model) { try { BufferedImage bufferedImage =
	 * generateQRCodeImage(qrDetailsPOJO); File outputfile = new File(
	 * "D:\\Web_DoAn\\Cines_Start\\src\\main\\resources\\static\\img\\image_" +
	 * qrDetailsPOJO.getId() + ".jpg"); ImageIO.write(bufferedImage, "jpg",
	 * outputfile);
	 * 
	 * model.addAttribute("qr", qrDetailsPOJO);
	 * 
	 * } catch (Exception e) { e.getMessage(); } return "redirect:/qr?success"; }
	 */

	public static BufferedImage generateQRCodeImage(BillUser barcodeText) throws Exception {
		StringBuilder str = new StringBuilder();
		str = str.append("Mã Hóa đơn:").append(barcodeText.getId()).append("| |").append("Họ Tên:")
				.append(barcodeText.getFirstName()).append(" ").append(barcodeText.getLastName()).append("| |")
				.append("Ngày mua:").append(barcodeText.getCreatedAt()).append("| |").append("Tên phim:")
				.append(barcodeText.getMovieName()).append("| |").append("Tên phòng:").append(barcodeText.getRoomName())
				.append("| |").append("Ngày chiếu:").append(barcodeText.getShowDate()).append("| |")
				.append("Giờ chiếu:").append(barcodeText.getShowTime()).append("| |")
				.append("Danh sách ghế:").append(barcodeText.getSeatList()).append("| |");
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		Hashtable hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = barcodeWriter.encode(str.toString(), BarcodeFormat.QR_CODE, 200, 200,hints);

		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}

}
