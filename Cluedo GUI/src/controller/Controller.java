package controller;

import java.awt.Color;

import colorschemes.BW;
import colorschemes.Emo;
import colorschemes.Kirita;
import colorschemes.Pastel;
import model.Model;
import view.SetupPopup;
import view.View;

/**
 * The controller translates the user's interactions with the view into actions
 * that the model will perform. In a stand-alone GUI client, user interactions
 * could be button clicks or menu selections. Depending on the context, a
 * controller may also select a new view -- for example, a web page of results
 * -- to present back to the user.
 * 
 * @author Patrick and Kirita
 *
 */
public class Controller {
	private Model model;
	private View view;

	public Controller(Model m) {
		this.model = m;
	}

	public void start(String scheme) {
		SetupPopup pop = new SetupPopup();
		pop.run();
		switch (scheme) {
		case "Kirita":
			Kirita k = new Kirita();
			model.setScheme(k);
			model.setStartingColor(k.BACKGROUND);
			view.redraw(model.getColorScheme().BACKGROUND);
			break;
		case "Emo":
			Emo emo = new Emo();
			model.setScheme(emo);
			model.setStartingColor(emo.BACKGROUND);
			view.redraw(model.getColorScheme().BACKGROUND);
			break;
		case "Pastel":
			Pastel p = new Pastel();
			model.setScheme(p);
			model.setStartingColor(p.BACKGROUND);
			view.redraw(model.getColorScheme().BACKGROUND);
			break;
		case "BW":
			BW b = new BW();
			model.setScheme(b);
			model.setStartingColor(b.BACKGROUND);
			view.redraw(model.getColorScheme().BACKGROUND);
			break;
		}
	}

	public void setView(View view) {
		this.view = view;
	}
}
