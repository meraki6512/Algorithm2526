class Solution {
    public int thirdMax(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) set.add(nums[i]);
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list, Collections.reverseOrder());
        return list.size() > 2 ? list.get(2) : list.get(0);
    }
}

/**
// best sol
class Solution {
    public int thirdMax(int[] nums) {
    long max = Long.MIN_VALUE;
    long secondMax = Long.MIN_VALUE;
    long thirdMax = Long.MIN_VALUE;
    for(int x : nums){
        if(x > max){
            thirdMax = secondMax;
            secondMax = max;
            max = x;
        }
        else if(x > secondMax && x != max){
            thirdMax = secondMax;
            secondMax = x;

        }
        else if(x > thirdMax && x != max && x != secondMax)
        thirdMax = x;
    }

       return thirdMax != Long.MIN_VALUE ? (int)thirdMax : (int)max; 
    }
}
 */