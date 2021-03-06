package com.x.okr.assemble.control.jaxrs.statistic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonElement;
import com.x.base.core.project.http.ActionResult;
import com.x.base.core.project.http.EffectivePerson;
import com.x.base.core.project.logger.Logger;
import com.x.base.core.project.logger.LoggerFactory;
import com.x.okr.assemble.control.jaxrs.statistic.exception.ExceptionWrapInConvert;

public class ActionStReportContentListDateByFilter extends BaseAction {

	private static  Logger logger = LoggerFactory.getLogger(ActionStReportContentListDateByFilter.class);

	protected ActionResult<List<WoOkrReportSubmitStatusDate>> execute( HttpServletRequest request, EffectivePerson effectivePerson, JsonElement jsonElement ) throws Exception {
		ActionResult<List<WoOkrReportSubmitStatusDate>> result = new ActionResult<>();
		Wi wrapIn = null;
		Boolean check = true;
		try {
			wrapIn = this.convertToWrapIn( jsonElement, Wi.class );
		} catch (Exception e ) {
			check = false;
			Exception exception = new ExceptionWrapInConvert( e, jsonElement );
			result.error( exception );
			logger.error( e, effectivePerson, request, null);
		}

		if( check ){
			try {
				result = new ActionDateList().execute( request, effectivePerson, wrapIn );
			} catch (Exception e) {
				result = new ActionResult<>();
				result.error( e );
				logger.warn( "system excute ExcuteDateList got an exception. " );
				logger.error( e );
			}
		}
		return result;
	}
	
	public static class Wi extends WrapInFilterOkrStatisticReportContent {
	}
}