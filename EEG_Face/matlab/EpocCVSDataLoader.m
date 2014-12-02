

function [markers,data] = EpocCVSDataLoader(dirName)
F = dir(strcat(dirName,'*.CSV'));
markers = [];
data = [];
for ii = 1:length(F)
    filename = strcat(dirName,F(ii).name);
    disp(strcat('Reading from ',filename));
    file_data = csvread(filename,1,0);% read data from file
    file_markers = file_data(1:size(file_data,1),36); %extract markers
    %[class,nr_samples] = hist(markers,unique(markers));
    %disp('loaded markers: \n');
    %disp(class);
    %disp(nr_samples);
    data = [data; file_data(1:size(file_data,1),3:16)]; %extract only channel data
    markers = [markers; file_markers];
end

