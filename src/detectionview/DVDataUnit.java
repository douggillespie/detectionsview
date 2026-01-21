package detectionview;

import Acquisition.AcquisitionProcess;
import PamguardMVC.PamProcess;
import PamguardMVC.PamRawDataBlock;
import clipgenerator.ClipDataUnit;

public class DVDataUnit extends ClipDataUnit {

	private DVProcess dvProcess;

	public DVDataUnit(DVProcess dvProcess, long timeMilliseconds, long triggerMilliseconds, long startSample, int durationSamples,
			int channelMap, String fileName, String triggerName, double[][] rawData, float sourceSampleRate) {
		super(timeMilliseconds, triggerMilliseconds, startSample, durationSamples, channelMap, fileName, triggerName,
				rawData, sourceSampleRate);
		this.dvProcess = dvProcess;
	}

	@Override
	protected AcquisitionProcess findDaqProcess() {
		PamRawDataBlock rawDB = dvProcess.getInputRawData();
		if (rawDB == null) {
			return null;
		}
		PamProcess rawParent = rawDB.getSourceProcess();
		if (rawParent instanceof AcquisitionProcess) {
			return (AcquisitionProcess) rawParent;
		}
		return null;
	}
}
