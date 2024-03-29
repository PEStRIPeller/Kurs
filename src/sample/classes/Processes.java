package sample.classes;

import sample.Controller;

import java.util.ArrayList;

public class Processes{
    private Scheduler scheduler;

    public ArrayList<Process> getList() {
        return list;
    }

    private ArrayList<Process> list;
    private Queue queue;

    public Processes(Scheduler scheduler){
        this.list = new ArrayList<>();
        this.scheduler = scheduler;
    }

    public void CheckByPriority() throws InterruptedException {
        Process runningProcess = null;
        Process processNew = null;
        boolean isFind = false;

        for (Process process:getList()) {
            if(process.getTypeState().equals(StateProcess.RUNNING)){
                runningProcess = process;
            }

            if(runningProcess!=null) {
                if (process.getPriority() > runningProcess.getPriority()) {
                    if (queue.add(process)) {
                        getList().get(getList().indexOf(runningProcess)).setTypeState(StateProcess.WAITING);
                        process.setTypeState(StateProcess.RUNNING);
                        processNew = process;
                        isFind = true;
                        break;
                    } else {
                        process.setTypeState(StateProcess.REJECTED);
                    }
                }
            }
        }
        if(isFind){
            scheduler.deleteBlock(scheduler.findById(runningProcess.getId()));
            toWork(processNew);
        }
    }

    public Process processMaxPriority(){
        int maxPriority=0;
        Process currentProcess = null;
        for (Process process:getList()) {
            if(process.getPriority()>maxPriority&&!process.getTypeState().equals(StateProcess.REJECTED)&&!process.getTypeState().equals(StateProcess.TERMINATED)){
                maxPriority=process.getPriority();
                currentProcess = process;
            }
        }
    return currentProcess;
    }


    public void Work() throws InterruptedException {
        Process currentProcess = processMaxPriority();
        queue = new Queue(scheduler);
        if(currentProcess!=null) {
            if (queue.add(currentProcess)) {
                toWork(currentProcess);
                queue.addConfirmedProcess(currentProcess);
            } else {
                getList().get(getList().indexOf(currentProcess)).setTypeState(StateProcess.REJECTED);
                queue.addRejectedProcess(currentProcess);
            }
        }
    }

    private void toWork(Process process) throws InterruptedException {
        getList().get(getList().indexOf(process)).setTypeState(StateProcess.RUNNING);
        MemoryBlock memoryBlock = new MemoryBlock(process.getId(), queue.getStart() + 1, queue.getStart() + process.getSize()+1);
        scheduler.add(memoryBlock);
        scheduler.getMemoryBlocks().sort(MemoryBlock.byAsc);
        Controller.Refresh();
        Thread.sleep(process.getTime()*1000);
        process.setTypeState(StateProcess.TERMINATED);
        scheduler.deleteBlock(memoryBlock);
        Controller.Refresh();
    }

    public void ChangePriority(int id, int priority){
        for (Process process:this.list) {
            if(process.getId()==id){
                process.setPriority(priority);
            }
        }
    }
}
