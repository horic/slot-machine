package com.paypal.billsafe.dojos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import akka.actor.UntypedActor;

public class SlotSearchAgent extends UntypedActor {


	@Override
	public void onReceive(Object message) throws Exception {

		if (message instanceof SlotSearchAgentPayload) {
			SlotSearchAgentPayload payload = (SlotSearchAgentPayload) message;
			System.out.println(getSelf() + " is seeking in "
					+ payload.getFile());
			WordFinder finder = createWordFinder(payload);

			if (finder == null) {
				throw new RuntimeException();
			}

			SlotSearchAgentResult result = null;

			if (finder.find(payload.getNeedle())) {
				result = new SlotSearchAgentResult(payload.getFile().getName(),
						MessageType.FOUND);
			} else {
				result = new SlotSearchAgentResult(payload.getFile().getName(),
						MessageType.NOT_FOUND);
			}
			getSender().tell(result, getSelf());

		} else {
			unhandled(message);
		}
	}

	private WordFinder createWordFinder(SlotSearchAgentPayload payload)
			throws FileNotFoundException {

		WordFinder finder = new WordFinder(new BufferedReader(
				new InputStreamReader(new FileInputStream(payload.getFile()),
						Charset.forName("UTF-8"))));

		return finder;
	}

}
