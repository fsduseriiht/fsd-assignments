import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../shared/services/api.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  postDetail: any;

  constructor(private route: ActivatedRoute,
    private apiService: ApiService,
    private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(route => {
      let postId = route['params'].id;
      this.apiService.getPostById(postId).subscribe(post => this.postDetail = post);
    });
  }

  backToList() {
    this.router.navigate(['posts']);
  }

}
