package com.gasq.bdp.logn.utils;

import org.apache.log4j.Logger;

public class MyProgressMonitor implements SftpProgressMonitor {
	private static final Logger LOG = Logger.getLogger(MyProgressMonitor.class.getName());
    private long transfered;

    @Override
    public boolean count(long count) {
        transfered = transfered + count;
        LOG.debug("Currently transferred total size: " + transfered + " bytes");
        return true;
    }

    @Override
    public void end() {
        LOG.debug("Transferring done.");
    }

    @Override
    public void init(int op, String src, String dest, long max) {
        LOG.debug("Transferring begin.");
    }
}