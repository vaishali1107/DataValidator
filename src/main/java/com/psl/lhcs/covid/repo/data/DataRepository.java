package com.psl.lhcs.covid.repo.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.psl.lhcs.covid.constants.ApplicationConstants;
import com.psl.lhcs.covid.model.data.DownloadResponse;
import com.psl.lhcs.covid.model.data.FileRecords;
import com.psl.lhcs.covid.model.data.FileResponse;


public interface DataRepository extends JpaRepository<FileRecords, Integer> {
	@Query(value = "SELECT user_id,timeStamp,Hospital_Name FROM file_records e where e.hospital_name=:hospitalName", nativeQuery = true)
    public List<DownloadResponse> getDataForHospital(@Param("hospitalName")String hospitalName);
	
	@Query(value = "SELECT user_id,timeStamp,lab_name FROM file_records  e where e.lab_name=:labName", nativeQuery = true)
    public List<DownloadResponse> getDataForLab(@Param("labName")String labName);
	
    @Query(value = "SELECT  user_id,timestamp FROM file_records ", nativeQuery = true)
    public List<DownloadResponse> getAllData();

    
    @Query(value = "SELECT  file,file_name from  file_records f where f.timestamp=:timeStamp and f.user_id=:email", nativeQuery = true)
    public FileResponse getFile(@Param(ApplicationConstants.TIMESTAMP) String timeStamp,@Param(ApplicationConstants.EMAIL) String email);
}
