package com.vall.gameserver.mockup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doAnswer;

/**
 *
 * Created by Vall on 02.11.2016.
 */
public class TestRequestHandler {

    private final RequestHandler requestHandler = new RequestHandler();

    @Spy
    private final BaseGameServer mockBaseGameServer = new AtomicBaseGameServer();
//    private final BaseGameServer mockBaseGameServer = new BooleanBaseGameServer();
//    private final BaseGameServer mockBaseGameServer = new VolatileBaseGameServer();


    private MockHttpServletRequest mockHttpServletRequest;

    @Before
    public void onSetUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockHttpServletRequest = new MockHttpServletRequest();
        final MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("gs", mockBaseGameServer);
        mockHttpServletRequest.setSession(mockHttpSession);

//        doReturn("result").when(mockBaseGameServer).execute();

        doAnswer(invocation -> {
            Thread.sleep(new Random().nextInt(100) + 100);
            return "result";
        }).when(mockBaseGameServer).execute();

    }

    @Test
    public void testSimpleCall() throws Exception {

        assertEquals("result", requestHandler.handleRequest(mockHttpServletRequest));

    }

    @Test
    public void testPnowCall() throws Exception {

        mockBaseGameServer.trySetProcessingNow();

        assertEquals("pnow", requestHandler.handleRequest(mockHttpServletRequest));

    }

    @Test
    public void testPnowRelease() throws Exception {
        mockBaseGameServer.trySetProcessingNow();

        assertEquals("pnow", requestHandler.handleRequest(mockHttpServletRequest));

        mockBaseGameServer.releaseProcessingNow();

        assertEquals("result", requestHandler.handleRequest(mockHttpServletRequest));
    }


    @Test
    public void testDuplicateRequest() throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<Callable<String>> callableList = Arrays.asList(
                () -> requestHandler.handleRequest(mockHttpServletRequest),
                () -> requestHandler.handleRequest(mockHttpServletRequest)
        );

        final int iterationCount = 1000;

        final Boolean[] pnowResult = new Boolean[iterationCount];
        Arrays.fill(pnowResult, false);

        for (int i = 0; i < iterationCount; i++) {

            final int currentIteration = i;
            executor.invokeAll(callableList)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(s -> {
                        System.out.println("Result : " + s);
                        if ("pnow".equals(s)) {
                            pnowResult[currentIteration] = true;
                        }
                    });
        }

        executor.shutdown();

        //Analyze results
        boolean testResult = true;
        int succeeded = 0;
        int failed = 0;

        for (Boolean item : pnowResult) {
            if (item) {
                succeeded++;
            } else {
                failed++;
                testResult = item;
            }
        }

        System.out.println(String.format("Succeeded: %d, Failed: %d", succeeded, failed));

        assertTrue(testResult);




    }





}
