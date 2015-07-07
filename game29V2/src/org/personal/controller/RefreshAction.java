package org.personal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.personal.exception.PlayerException;
import org.personal.exception.RefreshException;
import org.personal.model.Player;
import org.personal.play.PlayManager;

public class RefreshAction extends HttpServlet {

	final private Logger logger = Logger
			.getLogger(org.personal.controller.RefreshAction.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.DEBUG, "*********************************");
		logger.log(Level.DEBUG, "RefreshAction:Received Request from "
				+ request.getRemoteAddr());

		String htmlString = "";
		try {
			String actionStr = request
					.getParameter(ActionConstants.ACTION_COMMAND);
			logger.log(Level.TRACE, "doGet: " + ActionConstants.ACTION_COMMAND
					+ ": " + actionStr);
			int playerId = 0;
			String playerNameStr;
			String playerPasswordStr;

			String playerIdStr = request
					.getParameter(ActionConstants.PARAM_PLAYER_ID_STR);
			if (playerIdStr != null) {
				playerId = Integer.valueOf(playerIdStr);
			}

			playerPasswordStr = request
					.getParameter(ActionConstants.PARAM_PLAYER_PASS_STR);
			playerNameStr = request.getParameter(ActionConstants.PARAM_PLAYER_NAME_STR);
			PlayManager playMgr = PlayManager.getInstance();
			Player player = playMgr.getValidGamePlayer(playerId, playerNameStr,
					playerPasswordStr);
			logger.log(Level.DEBUG, "RefreshAction:Received Request from "
					+ player.getPlayerName());

			if (actionStr.equalsIgnoreCase(ActionConstants.REFRESH_ACTION)) {

				htmlString = playMgr.getHtmlForGame(player);
			}
		} catch (RefreshException re) {
			logger.log(Level.DEBUG, "doGet: Refresh Exception due to "
					+ re.getMessage());
			htmlString = "Problem refreshing the page... Please contact administrator if it persists...";
		} catch (PlayerException pe) {
			logger.log(Level.ERROR, "doGet: PlayerException due to "
					+ pe.getMessage());
			htmlString = "Problem refreshing the page... Please contact administrator if it persists...";
		} 

		response.getWriter().write(
				"<xml><xmlMsg><![CDATA[" + htmlString + "]]></xmlMsg></xml>");

	}
}
