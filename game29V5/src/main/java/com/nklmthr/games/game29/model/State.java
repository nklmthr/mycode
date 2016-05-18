package com.nklmthr.games.game29.model;

public interface State {
	State transition(Game game, Event event);
	
	String getSection11(Game game, Event event);
	String getSection12(Game game, Event event);
	String getSection13(Game game, Event event);
	
	String getSection21(Game game, Event event);
	String getSection22(Game game, Event event);
	String getSection23(Game game, Event event);
	
	String getSection31(Game game, Event event);
	String getSection32(Game game, Event event);
	String getSection33(Game game, Event event);
	
}
