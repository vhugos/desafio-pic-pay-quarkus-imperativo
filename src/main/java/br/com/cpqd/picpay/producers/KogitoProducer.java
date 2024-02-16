package br.com.cpqd.picpay.producers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class KogitoProducer {
    @Inject
    @Channel("createDeviceQueue")
    Emitter<String> emitter;

    public void procucer(String dto) {
        emitter.send(dto).toCompletableFuture().join();
    }
}
