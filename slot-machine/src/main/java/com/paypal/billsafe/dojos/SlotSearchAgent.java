package com.paypal.billsafe.dojos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import javax.management.RuntimeErrorException;

import akka.actor.UntypedActor;

public class SlotSearchAgent extends UntypedActor {

    @Override
    public void onReceive(Object message) {

        if (message instanceof SlotSearchAgentPayload) {
            SlotSearchAgentPayload payload = (SlotSearchAgentPayload) message;
            System.out.println(getSelf() + " is seeking in " + payload.getFile());
            WordFinder finder = new WordFinder();

            if (finder == null) {
                throw new RuntimeException();
            }

            SlotSearchAgentResult result = null;

            if (finder.find(payload.getNeedle(), createReader(payload.getFile()))) {
                result = new SlotSearchAgentResult(payload.getFile().getName(), MessageType.FOUND);
            } else {
                result = new SlotSearchAgentResult(payload.getFile().getName(), MessageType.NOT_FOUND);
            }
            getSender().tell(result, getSelf());

        } else {
            unhandled(message);
        }
    }


    
    private Reader createReader(File inFile) {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(inFile), Charset.forName("UTF-8")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
