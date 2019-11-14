package br.com.categoria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class StreamLambdaHandler implements RequestHandler <AwsProxyRequest, AwsProxyResponse> {
	
	private static final Logger log = LoggerFactory.getLogger(StreamLambdaHandler.class);
	
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(CategoriaApplication.class);
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }
    
    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
		log.info(">>>>>>>>>> Handling REQUEST - LAMBDA  >>>>>>>>>> received: " + awsProxyRequest.toString());
		log.info(">>>>>>>>>> Handling REQUEST - LAMBDA  >>>>>>>>>> context: " + context.toString());
		log.info(">>>>>>>>>> Handling REQUEST - LAMBDA  >>>>>>>>>> endpoint path: " + awsProxyRequest.getPath());
		log.info(">>>>>>>>>> Handling REQUEST - LAMBDA  >>>>>>>>>> endpoint method : " + awsProxyRequest.getHttpMethod());
		log.info(">>>>>>>>>> Handling REQUEST - LAMBDA  >>>>>>>>>> endpoint resource: " + awsProxyRequest.getResource());

		return handler.proxy(awsProxyRequest, context);
	}

}
