package me.kaward.spongepowered.spongechat.context;

import java.util.Map;

public interface ContextHandler<Response>
{

	public Map<Integer, ContextExecutor> subscription();

	public ContextExecutor getAction(Integer id);

	public ContextExecutor getNext();

	public ContextExecutor onCompleted();

	public ContextExecutor onFinished(final int reason, final Response argument);

	public boolean canContinue();

	public boolean canBack();

	public void setAction(Integer id, ContextExecutor executor);

	public void addAction(ContextExecutor executor);

	public void back();

	public void next();

	public int size();

	public int now();
}
