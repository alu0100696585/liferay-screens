package com.liferay.mobile.screens.audiencetargeting.interactor.loadscreenlets;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v62.dlfileentry.DLFileEntryService;
import com.liferay.mobile.screens.audiencetargeting.AudienceTargetingListener;
import com.liferay.mobile.screens.audiencetargeting.UserContext;
import com.liferay.mobile.screens.audiencetargeting.interactor.AudienceTargetingCallback;
import com.liferay.mobile.screens.audiencetargeting.interactor.AudienceTargetingResult;
import com.liferay.mobile.screens.audiencetargeting.interactor.requestcontent.AudienceTargetingContentEvent;
import com.liferay.mobile.screens.base.interactor.BaseRemoteInteractor;
import com.liferay.mobile.screens.base.interactor.JSONObjectCallback;
import com.liferay.mobile.screens.base.interactor.JSONObjectEvent;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.service.v62.ScreensAudienceTargetingService;
import com.liferay.mobile.screens.util.LiferayLogger;

import org.json.JSONObject;

/**
 * @author Javier Gamarra
 */
public class AudienceTargetingLoadScreenletsInteractorImpl
	extends BaseRemoteInteractor<AudienceTargetingListener>
	implements AudienceTargetingLoadScreenletsInteractor {

	public AudienceTargetingLoadScreenletsInteractorImpl(int targetScreenletId) {
		super(targetScreenletId);
	}

	public void getScreenlets(String screenletApp, Integer groupId, UserContext userContext)
		throws Exception {

		getScreenlets(screenletApp, groupId, null, userContext);
	}

	public void getScreenlets(String screenletApp, Integer groupId, String placeholder, UserContext userContext)
		throws Exception {

		JSONObject jsonObject = userContext.toJSON();

		ScreensAudienceTargetingService service = getAudienceTargetingService();
		service.getContent(screenletApp, groupId, placeholder, jsonObject, null);
	}

	public void onEvent(AudienceTargetingScreenletsLoadedEvent event) {
		if (!isValidEvent(event)) {
			return;
		}

		if (event.isFailed()) {
			getListener().onFailure(event.getException());
		}
		else {
			getListener().onSuccess(event);
		}
	}

	private ScreensAudienceTargetingService getAudienceTargetingService() {
		Session session = SessionContext.createSessionFromCurrentSession();
		session.setCallback(new AudienceTargetingLoadScreenletsCallback(getTargetScreenletId()));
		return new ScreensAudienceTargetingService(session);
	}

}
