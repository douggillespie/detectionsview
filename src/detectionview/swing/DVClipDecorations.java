package detectionview.swing;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import PamguardMVC.PamDataUnit;
import clipgenerator.ClipDataUnit;
import clipgenerator.clipDisplay.ClipDisplayDecorations;
import clipgenerator.clipDisplay.ClipDisplayMarker;
import clipgenerator.clipDisplay.ClipDisplayPanel;
import clipgenerator.clipDisplay.ClipDisplayUnit;
import detectionview.DVControl;
import detectionview.annotate.DVAnnotationWrapper;
import soundPlayback.ClipPlayback;

public class DVClipDecorations extends ClipDisplayDecorations {

	private DVControl dvControl;
	private PamDataUnit trigData;

	public DVClipDecorations(DVControl dvControl, ClipDisplayUnit clipDisplayUnit) {
		super(clipDisplayUnit);
		this.dvControl = dvControl;
	}

	@Override
	public JPopupMenu addDisplayMenuItems(JPopupMenu basicMenu) {

		ClipDataUnit clipDataUnit = getClipDisplayUnit().getClipDataUnit();
		trigData = getClipDisplayUnit().getTriggerDataUnit();
		
		JPopupMenu menu = super.addDisplayMenuItems(basicMenu);
		JMenuItem menuItem = new JMenuItem("Play clip");
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				playClip();
			}
		});
		menu.add(menuItem);

		DVAnnotationWrapper anHand = dvControl.getDvProcess().getAnnotationHandler();
		
		ClipDisplayMarker marker = this.getClipDisplayUnit().getClipDisplayPanel().getClipDisplayMarker();
		// work out which units are highlighted 
		ClipDisplayPanel clipPanel = this.getClipDisplayUnit().getClipDisplayPanel();
		ArrayList<ClipDisplayUnit> highlighted = clipPanel.getHighlightedUnits();
		if (highlighted.size() > 0) {
			PamDataUnit[] units = new PamDataUnit[highlighted.size()];
			for (int i = 0; i < units.length; i++) {
				units[i] = highlighted.get(i).getTriggerDataUnit();
			}
			List<JMenuItem> manyMenu = anHand.getAnnotationMenuItems(null, null, units);
			if (manyMenu != null) {
				for (int i = 0; i < manyMenu.size(); i++) {
					menu.add(manyMenu.get(i));
				}
			}
		}
		else {
			if (anHand != null && trigData != null) {
				JMenuItem moreMenu = anHand.createAnnotationEditMenu(trigData);
				if (moreMenu != null) {
					menu.add(moreMenu);
				}
			}
		}
				
		return menu;
	}

	protected void playClip() {
		ClipDataUnit clipDataUnit = getClipDisplayUnit().getClipDataUnit();

		ClipPlayback.getInstance().playClip(clipDataUnit.getRawData(), clipDataUnit.getParentDataBlock().getSampleRate(), true);
	}

}
