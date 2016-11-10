/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.actuate.metrics.statsd;

import java.io.Closeable;

import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClientErrorHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.actuate.metrics.writer.Delta;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.util.StringUtils;

/**
 * A {@link MetricWriter} that pushes data to statsd. Statsd has the concept of counters
 * and gauges, but only supports gauges with data type Long, so values will be truncated
 * towards zero. Metrics whose name contains "timer." (but not "gauge." or "counter.")
 * will be treated as execution times (in statsd terms). Anything incremented is treated
 * as a counter, and anything with a snapshot value in {@link #set(Metric)} is treated as
 * a gauge.
 *
 * @author Dave Syer
 * @since 1.3.0
 */
public class StatsdMetricWriter implements MetricWriter, Closeable {

	private static final Log logger = LogFactory.getLog(StatsdMetricWriter.class);

	private final NonBlockingStatsDClient client;

	/**
	 * Create a new writer instance with the given parameters.
	 * @param host the hostname for the statsd server
	 * @param port the port for the statsd server
	 */
	public StatsdMetricWriter(String host, int port) {
		this(null, host, port);
	}

	/**
	 * Create a new writer with the given parameters.
	 * @param prefix the prefix to apply to all metric names (can be null)
	 * @param host the hostname for the statsd server
	 * @param port the port for the statsd server
	 */
	public StatsdMetricWriter(String prefix, String host, int port) {
		prefix = StringUtils.hasText(prefix) ? prefix : null;
		while (prefix != null && prefix.endsWith(".")) {
			prefix = prefix.substring(0, prefix.length() - 1);
		}
		this.client = new NonBlockingStatsDClient(prefix, host, port,
				new LoggingStatsdErrorHandler());
	}

	@Override
	public void increment(Delta<?> delta) {
		this.client.count(delta.getName(), delta.getValue().longValue());
	}

	@Override
	public void set(Metric<?> value) {
		String name = value.getName();
		if (name.contains("timer.") && !name.contains("gauge.")
				&& !name.contains("counter.")) {
			this.client.recordExecutionTime(name, value.getValue().longValue());
		}
		else {
			this.client.gauge(name, value.getValue().longValue());
		}
	}

	@Override
	public void reset(String name) {
		if (name.contains("counter.")) {
			this.client.gauge(name, 0L);
		}
	}

	@Override
	public void close() {
		this.client.stop();
	}

	private static final class LoggingStatsdErrorHandler
			implements StatsDClientErrorHandler {

		@Override
		public void handle(Exception e) {
			logger.debug("Failed to write metric. Exception: " + e.getClass()
					+ ", message: " + e.getMessage());
		}

	}

}
