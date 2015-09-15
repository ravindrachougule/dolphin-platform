package com.canoo.dolphin.client;

import com.canoo.dolphin.Constants;
import com.canoo.dolphin.client.impl.ClientEventDispatcher;
import com.canoo.dolphin.client.impl.ClientPresentationModelBuilder;
import com.canoo.dolphin.client.impl.ClientPresentationModelBuilderFactory;
import com.canoo.dolphin.client.v2.ClientBeanManager;
import com.canoo.dolphin.client.v2.Param;
import com.canoo.dolphin.impl.*;
import com.canoo.dolphin.impl.ClassRepository.FieldType;
import com.canoo.dolphin.impl.collections.ListMapper;
import javafx.application.Platform;
import org.opendolphin.core.Tag;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientModelStore;
import org.opendolphin.core.client.ClientPresentationModel;
import org.opendolphin.core.client.comm.HttpClientConnector;
import org.opendolphin.core.client.comm.OnFinishedHandler;
import org.opendolphin.core.comm.JsonCodec;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.canoo.dolphin.impl.ClassRepository.FieldType.DOLPHIN_BEAN;

public class ClientBeanManagerImpl extends BeanManagerImpl implements ClientBeanManager {

    private static final String POLL_ACTION = "ServerPushController:longPoll";

    private static final String RELEASE_ACTION = "ServerPushController:release";

    private final ClientDolphin dolphin;

    public ClientBeanManagerImpl(BeanRepository beanRepository, BeanBuilder beanBuilder, ClientDolphin dolphin) {
        super(beanRepository, beanBuilder);
        this.dolphin = dolphin;
    }

    public CompletableFuture<Void> send(String command, Param... params) {
        if (params != null && params.length > 0) {
            final PresentationModelBuilder builder = new ClientPresentationModelBuilder(dolphin)
                    .withType(DolphinConstants.DOLPHIN_PARAMETER);
            for (final Param param : params) {
                final FieldType type = DolphinUtils.getFieldType(param.getValue());
                final Object value = type == DOLPHIN_BEAN ? beanRepository.getDolphinId(param.getValue()) : param.getValue();
                builder.withAttribute(param.getName(), value, Tag.VALUE)
                        .withAttribute(param.getName(), DolphinUtils.mapFieldTypeToDolphin(type), Tag.VALUE_TYPE);
            }
            builder.create();
        }
        final CompletableFuture<Void> result = new CompletableFuture<>();
        dolphin.send(command, new OnFinishedHandler() {
            @Override
            public void onFinished(List<ClientPresentationModel> presentationModels) {
                result.complete(null);
            }

            @Override
            public void onFinishedData(List<Map> data) {

            }
        });
        return result;
    }
}
