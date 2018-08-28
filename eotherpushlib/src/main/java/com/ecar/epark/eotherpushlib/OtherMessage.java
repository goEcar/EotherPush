package com.ecar.epark.eotherpushlib;

public class OtherMessage {

	/**
	 * 平台注册应用秘钥
	 */
	private String appSecretKey;

	/**
	 * 平台注册应用ID
	 */
	private String appSecretId;

	/* =============推送必要参数============= */
	/**
	 * 应用token
	 */
	private String regId;

	/**
	 * 应用名（应用包名，一个应用的唯一标志）
	 */
	private String applicationId;
	/**
	 * 推送标题, 【必填，字数限制1~32】（魅族限制）
	 */
	private String title = "";
	/**
	 * 推送摘要（现在在通知栏的内容）, 【必填，字数限制1~100】（魅族限制）
	 */
	private String description = "";

	private String data = "";// 打开界面时，应用要使用到的市局
	/* =============推送必要参数============= */

	/* =============指定应用打开界面并传递界面数据============= */
	/**
	 * 页面路径不为空时 打开具体的界面、 否则 打开应用启动页
	 */
	private String activityPath = "";
	/**
	 * 命名空间：应用保护的过滤标志，命名空间，主机名，路径（限制路径），目前处理为若有，三者都不能为空
	 */
	private String filterScheme = "";

	private String filterHost = "";

	private String filterPath = "";

	// 启动模式：标准模式 、singleTop、还是singleTask
	private String filterLaunchMode = "";

	// 隐式意图启动一个界面需要：action（前端设置默认值）、categoray(让前端设置默认)、data（data包括命名空间、主机名、路径等--最好配置，防止不同系统版本、不同厂商有要求）
	private String filterAction = "intent";
	/* =============指定应用打开界面并传递界面数据============= */

	// 打印字段
	private String deviceType = "";

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	private String logContent = "";

	protected OtherMessage(Builder builder) {
		this.appSecretKey = builder.appSecretKey;
		this.appSecretId = builder.appSecretId;
		this.regId = builder.regId;
		this.applicationId = builder.applicationId;
		this.title = builder.title;
		this.description = builder.description;
		this.data = builder.data;
		this.activityPath = builder.activityPath;
		this.filterScheme = builder.filterScheme;
		this.filterHost = builder.filterHost;
		this.filterPath = builder.filterPath;
		this.filterLaunchMode = builder.filterLaunchMode;
		this.filterAction = builder.filterAction;
		this.deviceType = builder.deviceType;
		this.logContent = builder.logContent;
	}

	public String getAppSecretKey() {
		return appSecretKey;
	}

	public void setAppSecretKey(String appSecretKey) {
		this.appSecretKey = appSecretKey;
	}

	public String getAppSecretId() {
		return appSecretId;
	}

	public void setAppSecretId(String appSecretId) {
		this.appSecretId = appSecretId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicatonId) {
		this.applicationId = applicatonId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getActivityPath() {
		return activityPath;
	}

	public void setActivityPath(String activityPath) {
		this.activityPath = activityPath;
	}

	public String getFilterScheme() {
		return filterScheme;
	}

	public void setFilterScheme(String filterScheme) {
		this.filterScheme = filterScheme;
	}

	public String getFilterHost() {
		return filterHost;
	}

	public void setFilterHost(String filterHost) {
		this.filterHost = filterHost;
	}

	public String getFilterPath() {
		return filterPath;
	}

	public void setFilterPath(String filterPath) {
		this.filterPath = filterPath;
	}

	public String getFilterLaunchMode() {
		return filterLaunchMode;
	}

	public void setFilterLaunchMode(String filterLaunchMode) {
		this.filterLaunchMode = filterLaunchMode;
	}

	public String getFilterAction() {
		return filterAction;
	}

	public void setFilterAction(String filterAction) {
		this.filterAction = filterAction;
	}

	public static final class Builder {

		public OtherMessage build() {
			return new OtherMessage(this);
		}

		/**
		 * 平台注册应用秘钥
		 */
		private String appSecretKey;

		/**
		 * 平台注册应用ID
		 */
		private String appSecretId;

		/* =============推送必要参数============= */
		/**
		 * 应用token
		 */
		private String regId;

		/**
		 * 应用名（应用包名，一个应用的唯一标志）
		 */
		private String applicationId;
		/**
		 * 推送标题, 【必填，字数限制1~32】（魅族限制）
		 */
		private String title = "";
		/**
		 * 推送摘要（现在在通知栏的内容）, 【必填，字数限制1~100】（魅族限制）
		 */
		private String description = "";

		private String data = "";// 打开界面时，应用要使用到的市局 .json格式，方便对接
		/* =============推送必要参数============= */

		/* =============指定应用打开界面并传递界面数据============= */
		/**
		 * 页面路径不为空时 打开具体的界面、 否则 打开应用启动页
		 */
		private String activityPath = "";
		/**
		 * 命名空间：应用保护的过滤标志，命名空间，主机名，路径（限制路径），目前处理为若有，三者都不能为空
		 */
		private String filterScheme = "unitehnreader";

		private String filterHost = "nativepage";

		private String filterPath = "/book/detail";

		// 启动模式：标准模式 、singleTop、还是singleTask
		private String filterLaunchMode = "";// 0x4000000 表示singleTask

		// 隐式意图启动一个界面需要：action、categoray、data（data包括命名空间、主机名、路径等）
		private String filterAction = "intent";
		/* =============指定应用打开界面并传递界面数据============= */

		// 打印字段
		private String deviceType = "";
		private String logContent = "";

		public Builder appSecretKey(String appSecretKey) {
			if (!OtherMessageUtil.isEmpty(appSecretKey)) {
				this.appSecretKey = appSecretKey;
			}
			return this;
		}

		public Builder appSecretId(String appSecretId) {
			if (!OtherMessageUtil.isEmpty(appSecretId)) {
				this.appSecretId = appSecretId;
			}
			return this;
		}

		public Builder regId(String regId) {
			this.regId = regId;
			return this;
		}

		public Builder applicationId(String applicatonId) {
			this.applicationId = applicatonId;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder data(String data) {
			this.data = data;
			return this;
		}

		public Builder activityPath(String activityPath) {
			if (!OtherMessageUtil.isEmpty(activityPath)) {
				this.activityPath = activityPath;
			}
			return this;
		}

		public Builder filterScheme(String filterScheme) {
			if (!OtherMessageUtil.isEmpty(filterScheme)) {
				this.filterScheme = filterScheme;
			}
			return this;
		}

		public Builder filterHost(String filterHost) {
			if (!OtherMessageUtil.isEmpty(filterHost)) {
				this.filterHost = filterHost;
			}
			return this;
		}

		public Builder filterPath(String filterPath) {
			if (!OtherMessageUtil.isEmpty(filterPath)) {
				this.filterPath = filterPath;
			}
			return this;
		}

		public Builder filterLaunchMode(String filterLaunchMode) {
			if (!OtherMessageUtil.isEmpty(filterLaunchMode)) {
				this.filterLaunchMode = filterLaunchMode;
			}
			return this;
		}

		public Builder filterAction(String filterAction) {
			if (!OtherMessageUtil.isEmpty(filterAction)) {
				this.filterAction = filterAction;
			}
			return this;
		}

		public Builder deviceType(String deviceType) {
			this.deviceType = deviceType;
			return this;
		}

		public Builder logContent(String logContent) {
			this.logContent = logContent;
			return this;
		}
	}

}
