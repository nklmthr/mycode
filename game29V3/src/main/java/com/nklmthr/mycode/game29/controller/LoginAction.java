package com.nklmthr.mycode.game29.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.nklmthr.mycode.game29.exception.PlayerException;
import com.nklmthr.mycode.game29.model.Player;
import com.nklmthr.mycode.game29.play.PlayManager;

public class LoginAction extends HttpServlet {
	
	private Logger logger = Logger
			.getLogger(com.nklmthr.mycode.game29.controller.LoginAction.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "*********************************");
		logger.log(Level.INFO, "LoginAction:Received Request from "
				+ request.getRemoteAddr());

		String action = request.getParameter(ActionConstants.ACTION_COMMAND);

		if (ActionConstants.LOGIN_ACTION.equalsIgnoreCase(action)) {
			try {
				int playerId = 0;
				String playerPasswordStr;
				String playerNameStr;

				String playerStr = request
						.getParameter(ActionConstants.PARAM_PLAYER_ID_STR);
				if (playerStr != null) {
					playerId = Integer.valueOf(playerStr);
				}

				playerPasswordStr = request
						.getParameter(ActionConstants.PARAM_PLAYER_PASS_STR);

				playerNameStr = request
						.getParameter(ActionConstants.PARAM_PLAYER_NAME_STR);

				Player player = new Player(playerId, playerNameStr,
						playerPasswordStr);

				PlayManager playMgr = PlayManager.getInstance();
				playMgr.addGamePlayerFromLogin(player);
				getServletConfig().getServletContext().getRequestDispatcher(
						"/play.jsp").forward(request, response);

			} catch (PlayerException ex) {
				logger.log(Level.ERROR, "PlayerExceptiondue to "
						+ ex.getMessage());
				getServletConfig().getServletContext().getRequestDispatcher(
						"/login.jsp?message=" + ex.getMessage()).forward(
						request, response);
			} 
		}
	}
}
