package edu.neu.DatastoreService;

public class Proposal {
    private String proposalId;
    private int nodeId;
    private long timestamp;

    public Proposal(String proposalId) {
        setProposalId(proposalId);
    }

    public Proposal() {
        this.proposalId = "";
        this.nodeId = 0;
        this.timestamp = 0;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setProposalId(String proposalId) throws NumberFormatException {
        this.proposalId = proposalId;
        String[] parts = proposalId.split("\\.");
        this.nodeId = Integer.parseInt(parts[0]);
        this.timestamp = Long.parseLong(parts[1]);
    }

    public boolean updateProposalId(String newProposalId) throws NumberFormatException {
        long newTimestamp = Long.parseLong(newProposalId.split("\\.")[1]);
        return newTimestamp > timestamp;
    }
}
