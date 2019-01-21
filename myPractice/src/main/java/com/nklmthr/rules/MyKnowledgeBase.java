package com.nklmthr.rules;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.conf.ClockTypeOption;

public class MyKnowledgeBase {
	public void setup() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.getKieClasspathContainer();
		KieSession ksession = kc.newKieSession("ksession1");

		ksession.fireAllRules();
	}

	public static void main(String[] args) throws IOException {
		MyKnowledgeBase m = new MyKnowledgeBase();
		BasicConfigurator.configure();
		// m.setup();
		m.kieFileSystem();

	}

	public KieFileSystem kieFileSystem() throws IOException {
		KieServices kieServices = KieServices.Factory.get();
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		//KieContainer kContainer = kieServices.getKieClasspathContainer();
		KieModuleModel kieModuleModel = kieServices.newKieModuleModel();

		KieBaseModel kieBaseModel1 = kieModuleModel.newKieBaseModel("KBase1").setDefault(true)
				.setEqualsBehavior(EqualityBehaviorOption.EQUALITY)
				.setEventProcessingMode(EventProcessingOption.STREAM);

		KieSessionModel ksessionModel1 = kieBaseModel1.newKieSessionModel("KSession1").setDefault(true)
				.setType(KieSessionModel.KieSessionType.STATEFUL).setClockType(ClockTypeOption.get("realtime"));

		//KieFileSystem kfs = kieServices.newKieFileSystem();
		kieFileSystem.writeKModuleXML(kieModuleModel.toXML());
		kieFileSystem.write("ruleSet1.drl", "test");

		return kieFileSystem;
	}
}
