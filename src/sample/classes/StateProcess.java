package sample.classes;

public enum StateProcess {
    NEW{
        @Override
        public String toString(){
            return "New";
        }
    },
    RUNNING{
        @Override
        public String toString(){
            return "Running";
        }
    },
    WAITING{
        @Override
        public String toString(){
            return "Waiting";
        }
    },
    READY{
        @Override
        public String toString(){
            return "Ready";
        }
    },
    TERMINATED{
        @Override
        public String toString(){
            return "Terminated";
        }
    },
    REJECTED{
        @Override
        public String toString(){return "Rejected"; }
    }
}
