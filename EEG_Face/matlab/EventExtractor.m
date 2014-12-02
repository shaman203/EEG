function [x,y]=EventExtractor(rawData,eventMarker,sampleSize, markers)

lines = 1;
disp('extracting events...');

events = find(markers == eventMarker);
%if(silence == false)
    for j=1:numel(events)
        startIndex = events(j) - sampleSize/2;
        endIndex = events(j)+ sampleSize/2 - 1;
        if(endIndex <= size(rawData,1))
            eventMatrix = rawData(startIndex:1:endIndex,:);
            
            x(lines,:) = reshape(eventMatrix,1,[]);
            y(lines) = eventMarker;
            lines = lines+1;
        end
    end
% else
%     for j=1:numel(events)-1
%         interval = events(j+1) - events(j);
%         if(interval > sampleSize)
%             startIndex = events(j)+ round(interval / 2) - sampleSize/2;
%             endIndex = events(j)+ round(interval / 2) + sampleSize/2;
%             
%             eventMatrix = rawData(startIndex:1:endIndex,:);
%             
%             x(lines,:) = reshape(eventMatrix,1,[]);
%             y(lines) = eventMarker;
%             lines = lines+1;
%         end
%     end
%end