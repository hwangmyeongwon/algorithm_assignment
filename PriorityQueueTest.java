class PriorityQueue{
    private int count;            	  // 우선순위 큐의 현재 원소수
    private int size;              	  // 배열의 크기
    private int increment;         	  // 배열 확장 단위
    private String[] itemArray;  // 우선순위 큐 원소를 저장하는 배열

    public PriorityQueue(){
        count = 0;   // itemArray[0]는 실제로 사용하지 않음
        size = 16;   //실제 최대 원소 수는 size - 1
        increment = 8;
        itemArray = new String[size];
    }

    public int currentSize(){		   // 우선순위 큐의 현재 원소수
        return count;
    }
    public void insert(String newKey){
        // 우선순위 큐에 원소 삽입
        if (count == size-1) PQFull();
        count++;   // 삽입 공간을 확보하고 원소의 삽입 위치를 밑에서부터 찾아 올라감
        int childLoc = count;
        int parentLoc = childLoc/2;
        while (parentLoc != 0) {
            if (newKey.compareTo(itemArray[parentLoc]) <= 0) {
                // 위치가 올바른 경우
                itemArray[childLoc] = newKey;             // 원소 삽입
                return;
            } else {   // 한 레벨 위의 위치로 이동
                itemArray[childLoc] = itemArray[parentLoc];
                childLoc = parentLoc;
                parentLoc = childLoc/2 ;
            }
        }
        itemArray[childLoc] = newKey;       // 최종 위치에 원소 삽입
    } // end insert()
    public void PQFull() {
        // itemArray가 만원이면
        size += increment;     // increment만큼 더 크게 확장
        String[] tempArray = new String[size];
        for (int i = 1; i < count; i++) {
            tempArray[i] = itemArray[i];
        }
        itemArray= tempArray;
    } // end PQFull()

    public String delete() {
        // 우선순위 큐로부터 원소 삭제
        if (count == 0) {                // 우선순위 큐가 공백인 경우
            return null;
        }
        else {
            // 변수 선언
            int currentLoc;
            int childLoc;
            String itemToMove= itemArray[count--];   // 이동시킬 원소
            String deletedItem= itemArray[1];;    // 삭제한 원소
            currentLoc = 1;
            childLoc = 2*currentLoc;
            while (childLoc <= count) {            // 이동시킬 원소의 탐색
                if (childLoc < count) {
                    if (itemArray[childLoc+1].compareTo(itemArray[childLoc]) > 0)
                        childLoc ++;
                }
                if (itemArray[childLoc].compareTo(itemToMove) > 0) {
                    itemArray[currentLoc]=itemArray[childLoc]; // 원소를 한 레벨
                    currentLoc = childLoc;                  // 위로 이동
                    childLoc = 2*currentLoc;
                } else {
                    itemArray[currentLoc]=itemToMove;  // 이동시킬 원소 저장
                    return deletedItem;
                }
            } // end while
            itemArray[currentLoc] = itemToMove;     // 최종 위치에 원소 저장
            return deletedItem;
        } // end if
    } // end delete()
} // end PriorityQueue class


public class PriorityQueueTest {
}
