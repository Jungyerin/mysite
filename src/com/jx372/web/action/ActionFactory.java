package com.jx372.web.action;

public abstract class ActionFactory {
	
	public abstract Action getAction(String actionName);			//미리 구현하기 어렵기 때문에 추상메소드로 만들어서 나중에 상속받아서 사용한다.
	
	

}
