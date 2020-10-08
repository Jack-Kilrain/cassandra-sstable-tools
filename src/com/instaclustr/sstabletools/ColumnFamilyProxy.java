package com.instaclustr.sstabletools;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.cassandra.db.DecoratedKey;

import java.util.Collection;

/**
 * Proxy to column family related functions of Cassandra backend.
 */
public interface ColumnFamilyProxy extends AutoCloseable {

    /**
     * Get readers for SSTable Index.db files for this column family.
     *
     * @return Collection of readers for SSTable Index.db files.
     */
    Collection<SSTableReader> getIndexReaders();

    /**
     * Get readers for SSTable Data.db files for this column family.
     *
     * @param rateLimiter Rate limiter for readers.
     * @return Collection of readers for SSTable Data.db files.
     */
    Collection<SSTableReader> getDataReaders(RateLimiter rateLimiter);

    /**
     * Get purge statistics reader.
     *
     * @param rateLimiter Rater limiter for reading.
     * @return Reader for purge statistics.
     */
    PurgeStatisticsReader getPurgeStatisticsReader(RateLimiter rateLimiter);

    /**
     * Format partition key into human readable format.
     *
     * @param key Decorated partition key.
     * @return Human readable partition key.
     */
    String formatKey(DecoratedKey key);

    /**
     * Is the column family using Date Tiered compaction strategy.
     *
     * @return True if column family is using Date Tiered compaction strategy.
     */
    boolean isDTCS();

    @Override
    void close();

    /**
     * @return the number of sstables
     */
    Integer numberOfSstables();
}
