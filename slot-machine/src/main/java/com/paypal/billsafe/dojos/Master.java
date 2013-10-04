package com.paypal.billsafe.dojos;

import java.io.File;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;

public class Master extends UntypedActor {

    private int numOfAgents = 0;

    private int agentResult = 0;

    private long startTime;



    @Override
    public void preStart() throws Exception {

        System.out.println("starting slot worker " + getSelf());

        startTime = System.currentTimeMillis();

        ActorRef agentRouter = getContext().actorOf(Props.create(SlotSearchAgent.class).withRouter(new RoundRobinRouter(7)), "agentRouter");
        File dir = new File("d:\\java.source\\dojo-20131001\\word-db\\");

        RandomWordGenerator gnr = new RandomWordGenerator(3);

        String randomWord = gnr.generateWord();

        System.out.println("try to find word " + randomWord);

        for (File file : dir.listFiles()) {
            agentRouter.tell(new SlotSearchAgentPayload(file, randomWord), getSelf());
            numOfAgents++;
        }
    }



    @Override
    public void onReceive(Object message) throws Exception {

        boolean found = false;

        if (message instanceof SlotSearchAgentResult) {

            agentResult++;

            if (((SlotSearchAgentResult) message).getResult() == MessageType.FOUND) {
                found = true;
            }

        } else {
            unhandled(message);
        }

        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        if (found) {
            System.out.println("Done. " + getSender() + "  found word in " + duration + " milliseconds");
        }

        if (found || agentResult == numOfAgents) {
            getContext().system().shutdown();
        }
    }

}
