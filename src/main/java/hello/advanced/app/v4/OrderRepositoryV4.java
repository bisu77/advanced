package hello.advanced.app.v4;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace logTrace;

    public void save(String itemId){

        AbstractTemplate<Void> logTemplate = new AbstractTemplate<>(logTrace){
            @Override
            protected Void call() {
                //저장로직
                if(itemId.equals("ex")){
                    throw new IllegalStateException("예외발생!");
                }
                sleep(1000);
                return null;
            }
        };

        logTemplate.excute("OrderRepository.save()");
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
