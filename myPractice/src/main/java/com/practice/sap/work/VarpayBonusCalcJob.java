package com.practice.sap.work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;

public class VarpayBonusCalcJob {
	private static String validUserList;
	private static String invalidUserList;
	private static String nonTargetPopulationUsers;

	public static void main(String[] args) {
		calculateBonus();
	}

	static void calculateBonus() {
		List<String> userNameList = generateUserNameList();
		List<String> userIdsInCompletedForm = generateCompleteFormUserIds();
		Collection userNamesToCalculateBonusFor = new ArrayList<String>(userNameList);
		Collection<String> userIds = null;
		System.out.println("1userNameList:" + userNameList + "\nuserIdsInCompletedForm:" + userIdsInCompletedForm);
		boolean notUpdateCompletedForm = true;
		if (notUpdateCompletedForm) {
			userIds = FindUserIdsByUsernames(userNameList);
			List<String> criteriaUsrIdsInCompletedForm = (List<String>) CollectionUtils
					.intersection(userIdsInCompletedForm, userIds);
//			System.out.println("3userNameList:" + userNameList + "\ncriteriaUsrIdsInCompletedForm:"
//					+ criteriaUsrIdsInCompletedForm);
			if (!criteriaUsrIdsInCompletedForm.isEmpty()) {
//				FindUsersByIds findUsersByIds = new FindUsersByIds(criteriaUsrIdsInCompletedForm);
//				List<UserBean> criteriaUsrBeansInCompletedForm = handler.execute(findUsersByIds);
				List<String> criteriaUsrBeansInCompletedForm = getUserNameFromId(criteriaUsrIdsInCompletedForm);
//				System.out.println("4criteriaUsrBeansInCompletedForm:" + criteriaUsrBeansInCompletedForm);
//				List<String> criteriaUsrNamesInCompletedForm = new ArrayList<String>(
//						criteriaUsrBeansInCompletedForm.size());
//				for (String criteriaUsrInCompletedForm : criteriaUsrBeansInCompletedForm) {
//					String username = getUserNameFromId(criteriaUsrInCompletedForm);
//					criteriaUsrNamesInCompletedForm.add(username);
//				}
				Predicate pred = new Predicate() {
					public boolean evaluate(Object userName) {
						return !criteriaUsrBeansInCompletedForm.contains(userName);
					}
				};
				CollectionUtils.filter(userNamesToCalculateBonusFor, pred);
				System.out.println("criteriaUsrBeansInCompletedForm:" + criteriaUsrBeansInCompletedForm
						+ "\nuserNamesToCalculateBonusFor:" + userNamesToCalculateBonusFor);
			}
		}

//		CalculatePayoutForEmployeesByProgramId cmd = new CalculatePayoutForEmployeesByProgramId(programId, formBean,
//				userNamesToCalculateBonusFor);
//		Map<String, List<String>> ret = legacyHandler.execute(params, cmd);
		Map<String, List<String>> ret = getReturnMap();
		if (ret != null) {
			ret.entrySet().stream().forEach(s -> System.out
					.println("**11toberemovedVRP-13561** ret:" + s.getKey() + ":" + s.getValue()));
		}
		StringBuilder nonTargetPopulationBuffer = new StringBuilder();
		StringBuilder invalidUserListBuffer = new StringBuilder();
		StringBuilder validUserListBuffer = new StringBuilder();
		for (Entry<String, List<String>> entry : ret.entrySet()) {
			if ("EXCLUDED_FROM_TARGET_POPULATION".equals(entry.getKey())) {
				for (String userId : entry.getValue()) {
					nonTargetPopulationBuffer.append(userId).append(", ");
				}
			}
			if ("USER_INVALID".equals(entry.getKey())) {
				for (String userId : entry.getValue()) {
					invalidUserListBuffer.append(userId).append(", ");
				}
			}
		}
		if (!StringUtils.isBlank(nonTargetPopulationBuffer.toString())) {
			nonTargetPopulationUsers = nonTargetPopulationBuffer.toString().substring(0,
					nonTargetPopulationBuffer.length() - 2);
		}
		if (!StringUtils.isBlank(invalidUserListBuffer.toString())) {
			invalidUserList = invalidUserListBuffer.toString().substring(0, invalidUserListBuffer.length() - 2);
			Collection<String> validUserIds = null;
			if (notUpdateCompletedForm) {
				validUserIds = userIds;
			} else {
//				FindUserIdsByUsernames findUserIdsByUsernamesCmd = new FindUserIdsByUsernames(userNameList);
//				validUserIds = handler.execute(findUserIdsByUsernamesCmd);
				validUserIds = FindUserIdsByUsernames(userNameList);
			}

//			System.out.println("**15toberemovedVRP-13561** validUserIds:" + validUserIds);
			if (/* RBPFilterUtil.isRBPEnabled(params) */true) {
				List<String> userIdsNotInTargetPopulation = ret.get("EXCLUDED_FROM_TARGET_POPULATION");
				
				CollectionUtils.filter(validUserIds, new Predicate() {
					@Override
					public boolean evaluate(Object userId) {
						return userIdsNotInTargetPopulation == null || !userIdsNotInTargetPopulation.contains(userId);
					}
				});
			}
//			FindUsersByIds findUsersByIds = new FindUsersByIds(new ArrayList<String>(validUserIds));
//			List<UserBean> validUserBeans = handler.execute(findUsersByIds);
//			for (UserBean employee : validUserBeans) {
//				validUserListBuffer
//						.append(CompMiscUtil.getUserDisplayName(params, messages, employee.getFullNameBean()))
//						.append(", ");
//			}
			validUserListBuffer.append(validUserIds.stream().collect(Collectors.joining(",")));
			if (!StringUtils.isBlank(validUserListBuffer.toString())) {
				validUserList = validUserListBuffer.toString().substring(0, validUserListBuffer.length() - 2);
			}
		}

		if (!StringUtils.isBlank(validUserList)) {
			System.out.println("**20toberemovedVRP-13561** validUserList:" + validUserList);
		}
		if (!StringUtils.isBlank(invalidUserList)) {
			System.out.println("**25toberemovedVRP-13561** invalidUserList:" + invalidUserList);
		}
		if (!StringUtils.isBlank(nonTargetPopulationUsers)) {
			System.out.println("**30toberemovedVRP-13561** nonTargetPopulationUsers:" + nonTargetPopulationUsers);
		}

	}

	private static List<String> getUserNameFromId(List<String> criteriaUsrInCompletedForm) {
		List<String> ret = new ArrayList<String>();
		for (String user : criteriaUsrInCompletedForm) {
			ret.add("user" + user);
		}
		return ret;
	}

	private static Map<String, List<String>> getReturnMap() {
		Map<String, List<String>> ret = new HashMap<>();
		ret.put("EXCLUDED_FROM_TARGET_POPULATION", Arrays.asList("2", "17"));
		ret.put("USER_INVALID", Arrays.asList("10", "11"));
		return ret;
	}

	private static List<String> generateCompleteFormUserIds() {
		List<String> ret = new ArrayList<String>();
		for (int i = 5; i < 10; i++) {
			ret.add("" + i);
		}		
		return ret;
	}

	private static List<String> FindUserIdsByUsernames(Collection<String> userNameList) {
		List<String> ret = new ArrayList<String>();
		for (String user : userNameList) {
			ret.add(user.split("user")[1]);
		}
		return ret;
	}

	private static List<String> generateUserNameList() {
		List<String> userNameList = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			userNameList.add("user" + i);
		}
		return userNameList;
	}
}
