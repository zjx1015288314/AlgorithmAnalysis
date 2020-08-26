package com.zjx.codingInterviewGuide.猿辅导;

import com.zjx.codingInterviewGuide.阿里.LISProblem;

import javax.jnlp.ClipboardService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class YFD2021Test出勤率 {

    static class Class {
        int stuNum; //学生数
        int tId;  //教师id
        String name;
        Set<Integer> set = new HashSet<>();
        int res = 0;

        public Class(int stuNum, int tId, String name) {
            this.stuNum = stuNum;
            this.tId = tId;
            this.name = name;
        }
    }

    static class INOutTime {
        int inTime;
        int outTime;

        public INOutTime(int inTime, int outTime) {
            this.inTime = inTime;
            this.outTime = outTime;
        }
    }

    static class CountTime {  //统计每个人进出时间
        int id; //自己id
        int tId; //老师id
        List<INOutTime> list = new ArrayList<>();

        public CountTime(int id, int tId) {
            this.id = id;
            this.tId = tId;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);  //总考勤记录
        int M = Integer.parseInt(str[1]); //班级个数
        Class[] classes = new Class[M];
        Map<Integer, Set> tsMap = new HashMap<>();
        Map<Integer, Integer> stMap = new HashMap<>();
        for (int i = 0; i < M; i++) {
            str = br.readLine().split(" ");

            int num = Integer.parseInt(str[0]);
            int tId = Integer.parseInt(str[1]);
            stMap.put(tId, tId);
            String name = str[2];
            Class c = new Class(num, tId, name);
            for (int j = 0; j < num; j++) {
                c.set.add(Integer.parseInt(str[j + 2]));
                stMap.put(Integer.parseInt(str[j + 2]), tId);
            }
            tsMap.put(tId, c.set);  //key= tid  value = 学生id set
        }

        Map<Integer, CountTime> countMap = new HashMap<>();  //人对应的进出时间
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            String InOrOutClass = str[0];
            int id = Integer.parseInt(str[1]); //老师或学生
            int time = Integer.parseInt(str[2]); //进出时间
            int tid = stMap.get(id);  //教师id
            if (!stMap.containsKey(id)) {
                CountTime countTime = new CountTime(id, tid);
                countMap.put(id, countTime);
            }
            //获取每个人的进出统计信息
            CountTime countTime = countMap.get(id);
            if (str.equals("IN")) {
                countTime.list.add(new INOutTime(time, Integer.MAX_VALUE));
            } else if (str.equals("OUt")) {
                int size = countTime.list.size();
                int inTime = countTime.list.get(size - 1).inTime;
                countTime.list.set(size - 1,new INOutTime(inTime,time));
            }
        }
        process(countMap,tsMap,stMap,classes);
    }

    public static void process(Map<Integer, CountTime> countMap,Map<Integer, Set> tsMap,
    Map<Integer, Integer> stMap ,Class[] classes){
        for (Map.Entry<Integer, CountTime> entry : countMap.entrySet()) {
             int id = entry.getKey();
             CountTime countTime = entry.getValue();
             for (INOutTime inOutTime : countTime.list){
                 //todo
             }
        }
    }


    public static void ttt(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();
        Map name = new HashMap();
        Map map = new HashMap();
        for (int i = 0; i < m; i++) {
            int size = scan.nextInt();
            int key = scan.nextInt();
            name.put(key, key);
            String str = scan.next();
            List list = new LinkedList();
            for (int j = 0; j < size; j++) {
                int id = scan.nextInt();
                list.add(id);
                name.put(id, key);
            }
            map.put(key, new Object[]{str, list});
        }

        Map lists = new LinkedHashMap<Integer, List>();
        while (n-- > 0) {
            String str = scan.next();
            int id = scan.nextInt();
            int header = (int) name.get(id);
            List list = (List) lists.get(header);
            if (!lists.containsKey(header)) {
                list = new ArrayList();
                Object[] obj = (Object[]) map.get(header);
                String clazz = (String) obj[0];
                list.add(clazz);
                list.add(new LinkedList());
                list.add(new LinkedList());
                list.add(new LinkedList());
                list.add(new LinkedList());
                lists.put(header, list);
            }
            List sit;
            int co = str.equals("IN") ? 1 : 2;
            co += id == header ? 0 : 2;
            sit = (List) list.get(co);
            sit.add(scan.nextInt());
        }

        process(name, map, lists);
    }

    private static void process(Map name, Map map, Map lists) {
        Map res = new TreeMap<String, Double>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Iterator itr = lists.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry obj = (Map.Entry) itr.next();
            List<Object> value = (List<Object>) obj.getValue();
            String clazz = (String) value.get(0);
            List<Integer> hs1 = (List<Integer>) value.get(1);
            List<Integer> he1 = (List<Integer>) value.get(2);
            List<Integer> ss1 = (List<Integer>) value.get(3);
            List<Integer> se1 = (List<Integer>) value.get(4);
            Integer[] hs = new Integer[hs1.size()];
            hs1.toArray(hs);
            Integer[] he = new Integer[he1.size()];
            he1.toArray(he);
            Integer[] ss = new Integer[ss1.size()];
            ss1.toArray(ss);
            Integer[] se = new Integer[se1.size()];
            se1.toArray(se);
            long[] l = ac(hs, he, ss, se);
            Object[] s = (Object[]) map.get((int) obj.getKey());
            List li = (List) s[1];
            Object[] ob = li.toArray();
            double d = (double) l[0] / (double) (l[1] * ob.length);
            res.put(clazz, d);
        }
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(res.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                double r = o2.getValue() - o1.getValue();
                return r > 0 ? 1 : r == 0 ? 0 : -1; // 升序
            }
        });
        for (Map.Entry<String, Double> entry : list) {
            System.out.println(entry.getKey());
        }
    }

    private static long[] ac(Integer[] hs, Integer[] he, Integer[] ss, Integer[] se) {
        if (hs.length != he.length || ss.length != se.length) {
            return new long[]{0, 0};
        }
        Arrays.sort(hs);
        Arrays.sort(he);
        Arrays.sort(ss);
        Arrays.sort(se);
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        long Sr = 0;
        long Tr = 0;
        int co = 0;
        boolean flag = false;
        while (i2 < he.length) {
            int start = hs[i1++];
            int end = he[i2++];
            while (min(hs[i1], he[i2], ss[i3], se[i4]) != end) {
                int time = min(hs[i1], he[i2], ss[i3], se[i4]);
            }
            ;
            flag = false;
        }
        return new long[]{Sr, Tr};
    }

    private static int min(int h, int i, int s, int i1) {
        return Math.min(h, Math.min(i, Math.min(s, i1)));
    }
}

