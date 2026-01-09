package detectionview;

import PamModel.PamDependency;
import PamModel.PamPluginInterface;

public class DVPlugin implements PamPluginInterface {
	
	private String jarFile;

	@Override
	public String getDefaultName() {
		return DVControl.unitType;
	}

	@Override
	public String getHelpSetName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setJarFile(String jarFile) {
		this.jarFile = jarFile;
	}

	@Override
	public String getJarFile() {
		return jarFile;
	}

	@Override
	public String getDeveloperName() {
		return "Doug Gillespie";
	}

	@Override
	public String getContactEmail() {
		return "pamguard@pamguard.org";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getPamVerDevelopedOn() {
		return "2.2.18";
	}

	@Override
	public String getPamVerTestedOn() {
		return "2.2.18";
	}

	@Override
	public String getAboutText() {
		return DVControl.unitTip;
	}

	@Override
	public String getClassName() {
		return DVControl.class.getName();
	}

	@Override
	public String getDescription() {
		return DVControl.unitType;
	}

	@Override
	public String getMenuGroup() {
		return "Displays";
	}

	@Override
	public String getToolTip() {
		return DVControl.unitTip;
	}

	@Override
	public PamDependency getDependency() {
		return null;
	}

	@Override
	public int getMinNumber() {
		return 0;
	}

	@Override
	public int getMaxNumber() {
		return 0;
	}

	@Override
	public int getNInstances() {
		return 0;
	}

	@Override
	public boolean isItHidden() {
		return false;
	}

	@Override
	public int allowedModes() {
		return PamPluginInterface.ALLMODES;
	}


}
