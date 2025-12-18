package com.viva.utils;

/*********************************************************************
 * COPYRIGHT: Comviva Technologies Limited
 *
 * This software is the sole property of Comviva and is protected
 * by copyright law and international treaty provisions. Unauthorized
 * reproduction or redistribution of this program, or any portion of
 * it may result in severe civil and criminal penalties and will be
 * prosecuted to the maximum extent possible under the law.
 *
 * Comviva reserves all rights not expressly granted. You may not
 * reverse engineer, decompile, or disassemble the software, except
 * and only to the extent that such activity is expressly permitted
 * by applicable law notwithstanding this limitation.
 *
 * THIS SOFTWARE IS PROVIDED TO YOU "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE. YOU ASSUME THE ENTIRE RISK AS TO THE ACCURACY
 * AND THE USE OF THIS SOFTWARE. COMVIVA SHALL NOT BE LIABLE FOR
 * ANY DAMAGES WHATSOEVER ARISING OUT OF THE USE OF OR INABILITY TO
 * USE THIS SOFTWARE, EVEN IF COMVIVA HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *********************************************************************/


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShelfLogger {

	String msisdn = "msisdn";

	/* The logger. */

	private Logger logger;

	/*
	 * 
	 * Constructor to get the Logger instance.
	 * 
	 * @param className the name of the logger to retrieve. If the named logger
	 * already exists, then the existing instance will be returned.
	 * 
	 */

	public ShelfLogger(final Class<?> className) {
		logger = LoggerFactory.getLogger(className);
	}



	/**
	 * 
	 * This method logs the statement with the INFO level (It designates
	 * informational messages that highlight the progress of the application at
	 * coarse-grained level). If this category is INFO enabled, then it proceeds to
	 * call all the registered appenders in this category and also higher in the
	 * hierarchy depending on the value of the additivity flag.
	 * 
	 * @param statement the statement to be logged
	 * 
	 **/

	public void info(final String statement) {
		logger.info(statement);
	}

	/**
	 * 
	 * This method logs the statement with the ERROR level (It designates error
	 * events that might still allow the application to continue running).
	 * 
	 * @param statement the statement to be logged
	 * 
	 **/

	public void error(final String statement) {
		logger.error(statement);
	}
}


