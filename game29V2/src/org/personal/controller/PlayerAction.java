package org.personal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.personal.exception.PlayerException;
import org.personal.model.Player;
import org.personal.play.PlayManager;

public class PlayerAction extends HttpServlet {

	private Logger logger = Logger
			.getLogger(org.personal.controller.PlayerAction.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*logger.log(Level.INFO, "*********************************");
		logger.log(Level.INFO,
				"PlayerAction:Received Request from " + request.getRemoteAddr());*/
		String htmlString = "";
		try {

			String actionStr = request
					.getParameter(ActionConstants.ACTION_COMMAND);
			/*logger.log(Level.DEBUG, "doGet: " + ActionConstants.ACTION_COMMAND
					+ " = " + actionStr);*/

			String playerIdStr = request
					.getParameter(ActionConstants.PARAM_PLAYER_ID_STR);
			int playerId = 0;
			if (playerIdStr != null) {
				playerId = Integer.valueOf(playerIdStr);
				logger.log(Level.DEBUG, "doGet: playerId = " + playerId);
			}
			String playerPasswordStr = request
					.getParameter(ActionConstants.PARAM_PLAYER_PASS_STR);
			logger.log(Level.TRACE, "doGet: playerPassword = "
					+ playerPasswordStr);

			String playerNameStr;
			playerNameStr = request
					.getParameter(ActionConstants.PARAM_PLAYER_NAME_STR);
			logger.log(Level.DEBUG, "PlayerAction:Received Request from "
					+ playerNameStr);
			PlayManager playMgr = PlayManager.getInstance();
			Player player = playMgr.getValidGamePlayer(playerId, playerNameStr,
					playerPasswordStr);
			logger.log(Level.DEBUG, "PlayerAction:Received Request from "
					+ player.getPlayerName());

			if (ActionConstants.DO_FIRST_DEAL_ACTION
					.equalsIgnoreCase(actionStr)) {

				htmlString = playMgr.doFirstDeal(player);
			} else if (ActionConstants.SET_NEW_CHALL_POINT_ACTION
					.equalsIgnoreCase(actionStr)) {

				int challengePoints = 0;
				String challengePointsStr = request
						.getParameter(ActionConstants.PARAM_CHALLENGE_POINT);
				if (challengePointsStr != null) {
					challengePoints = Integer.valueOf(challengePointsStr);
				}
				htmlString = playMgr.doPointsChallenge(player, challengePoints);
			} else if (ActionConstants.SET_CHALL_ACTION
					.equalsIgnoreCase(actionStr)) {
				String isDoubleStr = request
						.getParameter(ActionConstants.PARAM_CHALL_IS_DOUBLE);
				boolean isDouble = false;
				if (isDoubleStr != null) {
					isDouble = Boolean.valueOf(isDoubleStr);
				}
				htmlString = playMgr.finalizeChallenge(player, isDouble);
			} else if (ActionConstants.SET_REDOUBLE_ACTION
					.equalsIgnoreCase(actionStr)) {
				String isReDoubleStr = request
						.getParameter(ActionConstants.PARAM_CHALL_IS_REDOUBLE);
				boolean isReDouble = false;
				if (isReDoubleStr != null) {
					isReDouble = Boolean.valueOf(isReDoubleStr);
				}
				htmlString = playMgr.setReDoubleChallenge(player, isReDouble);
			} else if (ActionConstants.SET_TRUMP_ACTION
					.equalsIgnoreCase(actionStr)) {

				int trumpId = 0;
				String trumpIdStr = request
						.getParameter(ActionConstants.PARAM_TRUMP_ID_STR);
				if (trumpIdStr != null) {
					trumpId = Integer.valueOf(trumpIdStr);
				}
				htmlString = playMgr.doSetupTrump(player, trumpId);
			} else if (ActionConstants.MAKE_MOVE_ACTION
					.equalsIgnoreCase(actionStr)) {

				int suitNum = 0, rankNum = 0;
				String suitNumStr = request
						.getParameter(ActionConstants.PARAM_CARD_SUIT_NUM);
				String rankNumStr = request
						.getParameter(ActionConstants.PARAM_CARD_RANK_NUM);

				if (suitNumStr != null && rankNumStr != null) {
					suitNum = Integer.valueOf(suitNumStr);
					rankNum = Integer.valueOf(rankNumStr);
				}
				htmlString = playMgr.makeMove(player, suitNum, rankNum);
			} else if (ActionConstants.MAKE_TRUMP_SHOWN_ACTION
					.equalsIgnoreCase(actionStr)) {

				htmlString = playMgr.setTrumpShown(player);
			} else if (ActionConstants.START_NEW_GAME_ACTION
					.equalsIgnoreCase(actionStr)) {

				htmlString = playMgr.startNewAction(player);
			}
		} catch (PlayerException ex) {
			logger.log(Level.DEBUG,
					"doGet: PlayerExceptiondue to " + ex.getMessage());
			htmlString = ex.getMessage();
		}
		response.getWriter().write(
				"<xml><xmlMsg><![CDATA[" + htmlString + "]]></xmlMsg></xml>");
	}
}
