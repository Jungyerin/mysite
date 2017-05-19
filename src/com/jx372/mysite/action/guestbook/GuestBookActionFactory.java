package com.jx372.mysite.action.guestbook;

import com.jx372.web.action.Action;
import com.jx372.web.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
	
		Action action = null;

		if ("add".equals(actionName)) {
			action=new AddAction();

		} else if ("delete".equals(actionName)) {
			action=new DeleteAction();

		} else if("deleteform".equals(actionName))
		{
			action=new DeleteFormAction();
		}
		else { 
			action=new ListAction();
		}

		return action;
	}

}
