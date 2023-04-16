package cn.leiwspider;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;


public class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().sorted(Integer::compare).collect(Collectors.toList());
        List<List<Integer>> resultList = new ArrayList<>();
        resultList.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return 0;
            }
        });
        for(int i=0; i<list.size(); i++){
            int lk=i+1, rk=list.size()-1;
            if(i!=0 && Objects.equals(list.get(i), list.get(i - 1))){
                continue;
            }
            while(lk < rk){
                if(list.get(i)+list.get(lk)+list.get(rk) < 0){
                    lk++;
                }
                else if(list.get(i)+list.get(lk)+list.get(rk) > 0){
                    rk--;
                }
                else if(list.get(i)+list.get(lk)+list.get(rk) == 0){
                    if(lk != i+1 && Objects.equals(list.get(lk), list.get(lk - 1))){
                        lk++;
                        continue;
                    }
                    if(rk != list.size()-1 && Objects.equals(list.get(rk), list.get(rk + 1))){
                        rk--;
                        continue;
                    }
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(list.get(i));
                    tempList.add(list.get(lk++));
                    tempList.add(list.get(rk--));
                    resultList.add(tempList);
                }
            }
        }
        return resultList;
    }

    //分成三段 nums[0]-max, min-nums[len-1]
    public int search(int[] nums, int target) {
        if(nums.length == 0){
            return -1;
        }
        if(nums.length == 1){
            return nums[0]==target ? 0 : -1;
        }
        int lk=0, rk=nums.length - 1;
        while(lk <= rk){
            int mid = (lk + rk)/2;
            if(target == nums[mid]){
                return mid;
            }
            if(nums[0] <= nums[mid]){
                if(nums[0] <= target && target <= nums[mid]){
                    rk = mid - 1;
                }else{
                    lk = mid + 1;
                }
            }else{
                if(nums[mid] <= target && target <= nums[nums.length-1]){
                    lk = mid + 1;
                }else{
                    rk = mid - 1;
                }
            }
        }
        return -1;
    }

    public String fractionToDecimal(int numerator, int denominator) {
        long num1 = (long) numerator;
        long num2 = (long) denominator;
        if(num1 == 0) return "0";
        boolean posFlag;
        posFlag = num1 * num2 >= 0;
        if(num1 < 0){
            num1 = -num1;
        }
        if(num2 < 0){
            num2 = -num2;
        }
        if(num1 % num2 == 0){
            return String.valueOf(num1 / num2);
        }
        Integer lastNum = Math.toIntExact(num1 % num2);
        Integer lastInt = Math.toIntExact(num1 / num2);
        List<Integer> loopList = new ArrayList<>();
        List<Integer> noLoopList = new ArrayList<>();
        List<Integer> lastList = new ArrayList<>();

        while(!lastList.contains(lastNum)){
            lastList.add(lastNum);
            loopList.add((int) ((lastNum * 10)/num2));
            lastNum = Math.toIntExact((lastNum * 10) % num2);
            if(lastNum.equals(0)){ break;
//                String str = BigDecimal.valueOf((double) num1/num2).toString();
//                String[] strArr = str.split("\\.");
//                if(Objects.equals(strArr[strArr.length - 1], "0")){
//                    if(!posFlag){
//                        return "-"+ BigDecimal.valueOf(num1 / num2);
//                    }else {
//                        return BigDecimal.valueOf(num1/num2).toString();
//                    }
//                }else {
//                    if(!posFlag){
//                        return "-"+str;
//                    }else {
//                        return str;
//                    }
//                }
            }
        }
        int aim = lastList.indexOf(lastNum);

        StringBuilder strBuff = new StringBuilder();
        if(!posFlag){
            strBuff.append("-");
        }
        strBuff.append(lastInt).append(".");
        for(int i=0; i< aim; i++){
            strBuff.append(loopList.get(i));
        }
        strBuff.append("(");
        for(int i=aim; i< loopList.size(); i++){
            strBuff.append(loopList.get(i));
        }
        return strBuff.append(")").toString();
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums.length < 2){
            return false;
        }
        int[] nums1 = new int[nums.length];
        for(int i=0; i<nums1.length; i++){
            nums1[i] = nums[i] % k;
        }
        Map<Map<Integer, Integer>, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                Map<Integer, Integer> tempMap = new HashMap<>();
                tempMap.put(i, j);
                int sum;
                if(map.containsKey(tempMap)){
                    sum = map.get(tempMap);
                }else{
                    sum  = getSum(nums1, i, j, map);
                }
                if(sum == 0 || (sum >= k && sum % k == 0)){
                    return true;
                }
            }
        }
        return false;
    }
    private Integer getSum(int[] nums, int startIndex, int endIndex, Map<Map<Integer, Integer>, Integer> map){
        int sum = 0;
        Map<Integer, Integer> tempMap = new HashMap<>();
        tempMap.put(startIndex, endIndex);
        for(int i=startIndex; i<=endIndex; i++){

            int finalI = i;
            Map<Integer, Integer> map2 =  new HashMap<Integer, Integer>(){{put(startIndex, finalI);}};
            if(map.containsKey(map2)){
                sum = sum + map.get(map2);
            }else {
                sum+=nums[i];
            }
        }
        map.put(tempMap, sum);
        return sum;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        ListNode pre = new ListNode();
        pre.next = head;
        ListNode hold = pre;
        while(current != null){
            if(current.next != null && current.next.val == current.val){
                int val = current.val;
                while(current != null && current.val == val){
                    current = current.next;
                }
                pre.next = current;
            }else{
                pre = current;
                current = current.next;
            }
        }
        return hold.next;
    }

    public void printInputStream(File file) throws IOException {
        InputStream in = new BufferedInputStream(Files.newInputStream(file.toPath()), 10);
        System.out.println("available：" + in.available());
        byte[] bys = new byte[10];
        int readResult =  in.read(bys);
        char[] charArr = new char[bys.length];
        for(int i=0; i<bys.length; i++){
            charArr[i] = (char) bys[i];
        }
        System.out.println("readResult:" + readResult);
        System.out.println(Arrays.toString(charArr));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
