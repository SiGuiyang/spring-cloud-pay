package quick.pager.pay.app.client;

import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.model.pay.PayChannel;
import quick.pager.pay.request.pay.ChannelRequest;
import quick.pager.pay.response.Response;

public class ChannelClientFallback implements ChannelClient {

    @Override
    public Response<PayChannel> queryChannel(ChannelRequest request) {
        return new Response<>(ResponseStatus.PAY_CHANNEL_EXCEPTION.code, ResponseStatus.PAY_CHANNEL_EXCEPTION.msg);
    }
}
