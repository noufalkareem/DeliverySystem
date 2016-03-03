package com.mikkysoft.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mikkysoft.controller.NationalController;
import com.mikkysoft.controller.SectorController;
import com.mikkysoft.controller.SubscriberController;
import com.mikkysoft.controller.UnitController;
import com.mikkysoft.controller.UserController;
import com.mikkysoft.controller.ZoneController;
import com.mikkysoft.model.AccessType;
import com.mikkysoft.model.Sector;
import com.mikkysoft.model.Subscriber;
import com.mikkysoft.model.Unit;
import com.mikkysoft.model.User;
import com.mikkysoft.model.Zone;

/**
 * Servlet implementation class DeliveryServlet
 */
public class DeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int ZONE_ID = 11; // to be id = 1 for zone in the prod

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeliveryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			// TODO Auto-generated method stub
			HttpSession session = request.getSession();
			User user = null;
			Object userObj = session.getAttribute("user");//user object
			
			if (null == userObj) {
				user = UserController.getLoggedUser();//current logged user is set in the UserController
				User newUser = new User();
				newUser.setName(user.getName());
				newUser.setType(user.getType());
				newUser.setCircle(user.getCircle());
				request.getSession().setAttribute("user", newUser);// setting the user in the session
			} else {
				user = (User) userObj;
			}
			String zoneId = request.getParameter("zone");
			String sectorId = request.getParameter("sector");//get the sector id
			String unitId = request.getParameter("unit");// get the unit id
			String userId = request.getParameter("userid");// get the user id
			AccessType display = AccessType.NONE;
			int circleId = 0;
			if (null != zoneId) {
				display = AccessType.ZONE;
				circleId = Integer.parseInt(zoneId);
			}else if (null != sectorId) {
				display = AccessType.SECTOR;
				circleId = Integer.parseInt(sectorId);
			} else if (null != unitId) {
				display = AccessType.UNIT;
				circleId = Integer.parseInt(unitId);
			} else if(null != userId){
				display = AccessType.UNIT;
				circleId = Integer.parseInt(unitId);				
			}
			else {
				display = user.getType();
				circleId = user.getCircle().getUnitId();
			}

			if (display.equals(AccessType.NATIONAL)) {
				request.setAttribute("display", "national");
				List<Zone> zones = new NationalController()
						.getZones(circleId);
				request.setAttribute("zones", zones);
				getDisplay(request, response);
			} else	if (display.equals(AccessType.ZONE)) {
				request.setAttribute("display", "zone");
				List<Sector> sectors = new ZoneController()
						.getSectors(circleId);
				request.setAttribute("sectors", sectors);
				getDisplay(request, response);
			} else if (display.equals(AccessType.SECTOR)) {

				SectorController sectorController = new SectorController();
				List<Unit> units = sectorController.getUnitsBySector(circleId);
				request.setAttribute("units", units);
				request.setAttribute("display", "sector");
				getDisplay(request, response);

			} else if (display.equals(AccessType.UNIT)) {
				String mode = request.getParameter("mode");
				if(null != userId && mode.equals("edit")){
					int subscriberId = Integer.parseInt(userId);
					String month = request.getParameter("month");
					
					SubscriberController controller = new SubscriberController();
					controller.updateDelivery(subscriberId, month);
				}
				UnitController unitController = new UnitController();
				List<Subscriber> subscribers = unitController
						.getSubscribersByUnit(circleId);
				request.setAttribute("mode", mode);
				request.setAttribute("subscribers", subscribers);
				request.setAttribute("display", "unit");
				getUnitDisplay(request, response);

			} else if (display.equals(AccessType.ADMIN)) {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/jsp/admin.jsp");
				dispatcher.include(request, response);				
			} else {

			}
			// if(null!=unitId){
			// SectorController controller = new SectorController();
			// List<Unit> units =
			// controller.getUnitsBySector(Integer.parseInt(sectorId));
			// }else{
			// UnitController unitController = new UnitController();
			// List<Subscriber> subscribers =
			// unitController.getSubscribersByUnit(1);
			// request.setAttribute("subscribers", subscribers);
			// RequestDispatcher dispatcher =
			// request.getRequestDispatcher("/jsp/subscribers.jsp");
			// dispatcher.include(request, response);
			// }

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
//	private void getNationalDisplay(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher dispatcher = request
//				.getRequestDispatcher("/jsp/home.jsp");
//		dispatcher.include(request, response);
//	}
//
//	private void getZoneDisplay(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher dispatcher = request
//				.getRequestDispatcher("/jsp/home.jsp");
//		dispatcher.include(request, response);
//	}

	private void getDisplay(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/jsp/home.jsp");
		dispatcher.include(request, response);
	}

	private void getUnitDisplay(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/jsp/subscribers.jsp");
		dispatcher.include(request, response);
	}

}
